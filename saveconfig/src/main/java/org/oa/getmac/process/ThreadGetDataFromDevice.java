package org.oa.getmac.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.oa.getmac.model.Command;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.GlobalParam;
import org.oa.getmac.model.Task;
import org.oa.getmac.model.TaskComplited;
import org.oa.getmac.model.TerminalServer;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.MoreRepository;
import org.oa.getmac.repository.TaskComplitedLoggingRepository;
import org.oa.getmac.repository.TaskComplitedRepository;
import org.oa.getmac.repository.TaskRepository;
import org.oa.getmac.shell.ActiveOutConsole;
import org.oa.getmac.shell.Connection;
import org.oa.getmac.shell.OutConsole;
import org.oa.getmac.shell.TerminalServerConnection;
import org.oa.getmac.shell.TerminalSsh;
import org.oa.getmac.shell.TerminalTelnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
/*
 * This class Execute connect to devices, servers and save
 * return data to file
 */

import org.springframework.web.socket.WebSocketSession;

public class ThreadGetDataFromDevice extends Thread {
	private static Logger log = Logger.getLogger(ThreadGetDataFromDevice.class);
	private volatile boolean pause = false;
	private volatile boolean stop = false;
	private List<WebSocketSession> webSocketSessions;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private TaskComplitedRepository taskComplitedRepository;
	@Autowired
	private TaskComplitedLoggingRepository taskComplitedLoggingRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private MoreRepository moreRepository;
	@Autowired
	private GlobalParam globalParam;

	private final Object lockobject = new Object();
	private boolean isConnectedtoTerminalServer;
	private Connection connection;

	public ThreadGetDataFromDevice() {

	}

	public void send(String s) {
		for (WebSocketSession socketSession : this.webSocketSessions) {
			try {
				if ((socketSession != null) || socketSession.isOpen()) {
					socketSession.sendMessage(new TextMessage(s));
				}
			} catch (IOException e) {
				this.webSocketSessions.remove(socketSession);
				e.printStackTrace();
			}
		}
	}

	public void doStop() {
		this.stop = true;
	}

	public void doPause() {
		this.pause = true;
	}

	public void doResume() {
		synchronized (lockobject) {
			this.pause = false;
			lockobject.notify();
		}
	}

	public void pauseTread() {
		synchronized (lockobject) {
			while (this.pause == true) {
				try {
					lockobject.wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}

	public List<WebSocketSession> getWebSocketSessions() {
		return webSocketSessions;
	}

	public void setWebSocketSessions(List<WebSocketSession> webSocketSessions) {
		this.webSocketSessions = webSocketSessions;
	}

	public void run() {
		ActiveOutConsole activeOutConsole = new ActiveOutConsole();
		activeOutConsole.setWebSocketSessions(this.getWebSocketSessions());
		OutConsole outConsole = activeOutConsole;
		do {
			List<TaskComplited> taskCompliteds = taskComplitedRepository.findByWaitTask();
			Task task;

			if (taskCompliteds.isEmpty()) {
				break;
			}
			outConsole.setStatusTaskComplited("End ok");
			outConsole.sendMessage("not empty");
			TaskComplited taskComplited = taskCompliteds.get(0);
			outConsole.setTaskComplited(taskComplited);
			outConsole.setTaskComplitedLoggingRepository(taskComplitedLoggingRepository);
			task = taskRepository.findById(taskComplited.getTaskid());
			taskComplited.setStatus("started");
			outConsole.sendMessage("Task: " + task.getName());
			outConsole.sendMessage("type: " + taskComplited.getType());
			outConsole.sendMessage("StartTime: " + taskComplited.getStartTime().toString());
			taskComplitedRepository.update(taskComplited);
			List<Device> devices = deviceRepository.findByTask(task);
			List<Device> devicesIsOne = new ArrayList<Device>();

			for (Device device : devices) {
				if (device.isSwitchEnable() == true) {
					outConsole.sendMessage(device.getDeviceName());
					devicesIsOne.add(device);
				} else {
					outConsole.sendMessage(device.getDeviceName() + " is disabled");

				}

			}

			Map<TerminalServer, List<Device>> mapTerminalServerDevices = new HashMap<>();

			for (Device device : devicesIsOne) {
				if (mapTerminalServerDevices.containsKey(device.getTemplate().getTerminalServer()) == false) {
					mapTerminalServerDevices.put(device.getTemplate().getTerminalServer(), new ArrayList<Device>());
				}
				mapTerminalServerDevices.get(device.getTemplate().getTerminalServer()).add(device);
			}

			for (Map.Entry<TerminalServer, List<Device>> entry : mapTerminalServerDevices.entrySet()) {
				TerminalServer terminalServer = entry.getKey();
				List<Device> devicesForTServer = entry.getValue();

				switch (terminalServer.getDeviceName()) {
				case "telnet":
					for (Device device : devicesForTServer) {
						TerminalTelnet terminalTelnet = new TerminalTelnet();
						terminalTelnet.setPathTosave(globalParam.getSavePath());
						terminalTelnet.setOutConsole(outConsole);
						terminalTelnet.setTerminalServer(terminalServer);
						terminalTelnet.setDeviceRepository(deviceRepository);
						terminalTelnet.setDevice(device);
						connection = terminalTelnet;
						connection.setMoreList(moreRepository.getMoreToDo(device.getTemplate().getId()));
						connection.doAllCommand(device);
						pauseTread();
						if (stop == true) {
							continue;
						}
					}

					if (stop == true) {
						activeOutConsole.setStatusTaskComplited("Stopped manual");
						continue;
					}

					break;

				case "ssh":
					for (Device device : devicesForTServer) {
						TerminalSsh terminalSsh = new TerminalSsh();
						terminalSsh.setPathTosave(globalParam.getSavePath());
						terminalSsh.setOutConsole(outConsole);
						terminalSsh.setTerminalServer(terminalServer);
						terminalSsh.setDeviceRepository(deviceRepository);
						terminalSsh.setDevice(device);
						connection = terminalSsh;
						isConnectedtoTerminalServer = connection.connect();
						if (!isConnectedtoTerminalServer) {
							activeOutConsole.writeLog("Device: " + device.getDeviceIp() + " is not connected", "Error");
							continue;
						}

						connection.setMoreList(moreRepository.getMoreToDo(device.getTemplate().getId()));
						connection.doAllCommand(device);
						connection.disconnect();
						pauseTread();
						if (stop == true) {
							continue;
						}
					}
					if (stop == true) {
						activeOutConsole.setStatusTaskComplited("Stopped manual");
						continue;
					}

					break;

				default:
					outConsole.sendMessage("Test of server id : " + terminalServer.getId() + "\n");
					outConsole.sendMessage("Server name : " + terminalServer.getDeviceName() + "\n");
					outConsole.sendMessage("Server IP : " + terminalServer.getDeviceIp() + "\n");
					outConsole.sendMessage("Is rechable icmp : ");
					boolean isReachableIcmp = terminalServer.isReachableIcmp();
					outConsole.sendMessage(isReachableIcmp + "\n");
					if (!isReachableIcmp) {
						outConsole.writeLog("Terminal server: " + terminalServer.getDeviceIp() + "icmp is Unrichable",
								"Error");
						continue;
					}
					outConsole.sendMessage("Is rechable tcp port 22 : ");
					boolean isReachableTcp = terminalServer.isReachableTcp();
					outConsole.sendMessage(isReachableTcp + "\n");
					if (!isReachableTcp) {
						outConsole.writeLog(
								"Terminal server: " + terminalServer.getDeviceIp() + "port 22 is Unrichable", "Error");
						continue;
					}

					TerminalServerConnection terminalServerConnection = new TerminalServerConnection();
					terminalServerConnection.setPathTosave(globalParam.getSavePath());
					terminalServerConnection.setOutConsole(outConsole);
					terminalServerConnection.setTerminalServer(terminalServer);
					terminalServerConnection.setDeviceRepository(deviceRepository);
					connection = terminalServerConnection;

					isConnectedtoTerminalServer = connection.connect();
					if (!isConnectedtoTerminalServer) {
						outConsole.writeLog("Terminal server: " + terminalServer.getDeviceIp() + " is not connected",
								"Error");
						continue;
					}

					for (Device device : devicesForTServer) {
						connection.setMoreList(moreRepository.getMoreToDo(device.getTemplate().getId()));
						connection.doAllCommand(device);
						pauseTread();
						if (stop == true) {
							continue;
						}
					}
					connection.disconnect();
					if (stop == true) {
						outConsole.setStatusTaskComplited("Stopped manual");
						break;
					}
				}
			}
			taskComplited.setStatus(activeOutConsole.getStatusTaskComplited());
			taskComplited.setStopTime(new Date());
			taskComplitedRepository.update(taskComplited);

		} while (true);
	}

	public void writeToFile(Device device, Command command, String data) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat timeFormat = new SimpleDateFormat("HH_mm");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		String timeString = timeFormat.format(date);
		String stringPath = "/opt/backup/" + device.getDeviceName();

		String fileName = command.getCommand().replace(" ", "") + timeString;
		stringPath = stringPath + "/" + dateString + "/" + fileName;

		File file = new File(stringPath);
		Path path = Paths.get(stringPath);
		File directory = new File(path.getParent().toString());
		if (!file.exists()) {
			if (!directory.exists()) {
				directory.mkdirs();
			}
			try {
				if (file.createNewFile()) {
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writer;
		try {
			writer = new FileWriter(stringPath, false);

			writer.write(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setmanual(int idTask) {
		TaskComplited taskComplited = new TaskComplited();
		taskComplited.setTaskid(idTask);
		taskComplited.setStartTime(new Date());
		taskComplited.setStopTime(new Date());
		taskComplited.setStatus("wait");
		taskComplited.setType("manual");
		taskComplitedRepository.create(taskComplited);
	}

	public void setcron(int idTask) {
		TaskComplited taskComplited = new TaskComplited();
		taskComplited.setTaskid(idTask);
		taskComplited.setStartTime(new Date());
		taskComplited.setStopTime(new Date());
		taskComplited.setStatus("wait");
		taskComplited.setType("cron");
		taskComplitedRepository.create(taskComplited);
	}

	public void doTerminalServer() {

	}

}
