package org.oa.getmac.shell;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Command;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.GlobalParam;
import org.oa.getmac.model.More;
import org.oa.getmac.model.SaveConfig;
import org.oa.getmac.model.TerminalServer;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.GlobalParamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class TerminalSsh implements Connection {

	
	private static final long TIMEOUT_EXECUTE = 5000; // ms
	private TerminalServer terminalServer;
	private Session session;
	private Channel channel;
	private InputStream inStream;
	private OutputStream outStream;
	private static Logger log = Logger.getLogger(TerminalSsh.class);
	private StringBuilder resultConnection;
	private StringBuilder resultDisconnect;
	private StringBuilder resultCommand;
	private Map<String, String> moreMap = new HashMap<>();
	private List<More> moreList;
	private OutConsole outConsole;
	private String pathToSave;
	private Device device;
	
	public TerminalSsh() {
		
	}

	private DeviceRepository deviceRepository;

	public DeviceRepository getDeviceRepository() {
		return deviceRepository;
	}

	public void setDeviceRepository(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}

	public OutConsole getOutConsole() {
		return outConsole;
	}

	public void setOutConsole(OutConsole outConsole) {
		this.outConsole = outConsole;
	}

	@Override
	public boolean connect() {
		resultConnection = new StringBuilder();
		String message;
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(device.getUsername1(), device.getDeviceIp(), 22);
			
			session.setPassword(device.getPassword1());
			session.setConfig("StrictHostKeyChecking", "no");
			
			session.connect(10000);// timeoutconnect 20c
			channel = session.openChannel("shell");
			inStream = new BufferedInputStream(channel.getInputStream(), 20000);
			outStream = channel.getOutputStream();
			channel.connect();
			
			if (resultConnection.length() != 0) {
				resultConnection.delete(0, resultConnection.length());
			}

			byte[] buffer = new byte[1024];
			int lastByteBuffer = 0;
			long timeStart = System.currentTimeMillis();
			while (true) {

				if ((System.currentTimeMillis() - timeStart) > TIMEOUT_EXECUTE) {

					throw new CommandTimeOut();
				}

				if (inStream.available() > 0) {
					lastByteBuffer = inStream.read(buffer);
					String temp = new String(buffer, 0, lastByteBuffer);
					resultConnection.append(temp);
				}
				if (-1 != resultConnection.indexOf(device.getPromt1())) {
					break;
				}
			}
			//System.out.print("end");

		} catch (JSchException e2) {
			switch (e2.getMessage()) {
			case "timeout: socket is not established":
				
				message = "Device: " + device.getDeviceName();
				message += " IP:  " + device.getDeviceIp();
				message += " SSH Server not answer";				
				resultConnection.append("message");
				resultDisconnect = new StringBuilder(message);
				this.outConsole.sendMessage(message);
				this.outConsole.writeLog(message, "Error");
				this.outConsole.setStatusTaskComplited("End error");
				this.outConsole.setStatusTaskComplitedLogging("Error");
				break;
			case "Auth fail":
				
				message = "Device: " + device.getDeviceName();
				message += " IP:  " + device.getDeviceIp();
				message += "Check login or password SSH Server\n";
				
				
				resultConnection.append("message");
				resultDisconnect = new StringBuilder(message);
				this.outConsole.sendMessage(message);
				this.outConsole.writeLog(message, "Error");
				this.outConsole.setStatusTaskComplited("End error");
				this.outConsole.setStatusTaskComplitedLogging("Error");

				break;
			default:
				
				
				message = "Device: " + device.getDeviceName();
				message += " IP:  " + device.getDeviceIp();
				message += e2.getMessage();

				resultConnection.append("message");
				resultDisconnect = new StringBuilder(message);
				this.outConsole.sendMessage(message);
				this.outConsole.writeLog(message, "Error");
				this.outConsole.setStatusTaskComplited("End error");
				this.outConsole.setStatusTaskComplitedLogging("Error");
				
				break;
			}

			session.disconnect();
			return false;

		} catch (CommandTimeOut e) {
			resultConnection.append("\nTimeout promt\n");
			message = "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
			message += " Timeout promt";
			resultConnection.append(message);
			resultDisconnect = new StringBuilder(message);
			this.outConsole.sendMessage(message);
			this.outConsole.writeLog(message, "Error");
			this.outConsole.setStatusTaskComplited("End error");
			this.outConsole.setStatusTaskComplitedLogging("Error");
			log.error(e);
			return false;
		} catch (IOException e) {
			message = "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
			message += " IOException";
			resultConnection.append(message);
			resultDisconnect = new StringBuilder(message);
			this.outConsole.sendMessage(message);
			this.outConsole.writeLog(message, "Error");
			this.outConsole.setStatusTaskComplited("End error");
			this.outConsole.setStatusTaskComplitedLogging("Error");

			log.error(e);
			return false;
		}
		
		message = "\nConnected to Device: " + device.getDeviceName();
		message += " IP:  " + device.getDeviceIp();
		//log.info(message);
		resultConnection.append(message);
		resultDisconnect = new StringBuilder(message);
		this.outConsole.sendMessage(message);
		this.outConsole.writeLog(message, "OK");
		//System.out.println(resultConnection.toString());
		return true;
	}

	@Override
	public String getConnect() {
		return this.resultConnection.toString();
	}

	@Override
	public boolean disconnect() {
		String message = "";
		try {
			inStream.close();
			outStream.close();
			channel.disconnect();
			session.disconnect();
			message = "Disconnect from ";
			message += "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
		} catch (IOException e) {
			message = "IOException ";
			message += "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
			log.error(e);
		}
		
		resultDisconnect = new StringBuilder(message);
		this.outConsole.sendMessage("\n" + message);
		this.outConsole.writeLog(message, "OK");
		return true;
	}

	@Override
	public String getDisconnect() {
		return resultDisconnect.toString();
	}

	@Override
	public boolean command(String command, String waitFor, int timeout) {

		return false;
	}

	@Override
	public String getCommand() {

		return this.resultCommand.toString();
	}

	public void setMoreList(List<More> mores) {
		Map<String, String> mapMore = new HashMap();
		this.moreList = mores;
		for (More more : mores) {
			mapMore.put(more.getMore(), more.getMoreDo());
		}
		this.moreMap = mapMore;
	}

	public TerminalServer getTerminalServer() {
		return terminalServer;
	}

	public void setTerminalServer(TerminalServer terminalServer) {
		this.terminalServer = terminalServer;
	}

	@Override
	public boolean command(Device device, Command command) {
		resultCommand = new StringBuilder();
		if (resultCommand.length() != 0) {
			resultCommand.delete(0, resultCommand.length());
		}

		byte[] buffer = new byte[1024];
		int lastByteBuffer = 0;
		long timeStart = System.currentTimeMillis();

		StringBuilder tempSB = new StringBuilder();
		try {
			outStream.write(command.getCommand().getBytes());
			outStream.flush();
			while (true) {

				if ((System.currentTimeMillis() - timeStart) > (command.getWaitTime())) {
					throw new CommandTimeOut();
				}
				
				if (inStream.available() > 0) {
					lastByteBuffer = inStream.read(buffer);
					String temp = new String(buffer, 0, lastByteBuffer);
					tempSB.append(temp);
					for (String key : moreMap.keySet()) {
						int index = tempSB.indexOf(key);

						if (index > 0) {
							tempSB.delete(index, index + key.length());
							outStream.write(moreMap.get(key).getBytes());
							outStream.flush();
							timeStart = System.currentTimeMillis();

						}
					}
				}
				if (-1 != tempSB.indexOf(command.getWaitFor())) {
					//resultCommand.append(tempSB);
					//log.info(resultCommand);
					break;
				}
			}

			for (More more : moreList) {

				if (more.isIsdelete()) {
					int index;
					while ((index = tempSB.indexOf(more.getMoreDelete())) != -1) {
						tempSB.delete(index, index + more.getMoreDelete().length());
					}
				}

			}

			resultCommand.append(tempSB);
			//System.out.println(tempSB);

		} catch (CommandTimeOut e) {
			//System.out.println(tempSB);
			resultCommand.append(tempSB);
			String comment = "Device IP:" + device.getDeviceIp() + " Timeout promt wait for:" + command.getWaitFor()
					+ " command order:" + command.getOrder();
			resultCommand.append("\n" + comment);
			outConsole.setStatusTaskComplited("End error");
			outConsole.setStatusTaskComplitedLogging("Error");
			outConsole.writeLog(comment, "Error");
			log.error(e);
			if (command.isSave()) {
				SaveConfig.writeToFile(pathToSave,device, command, resultCommand.toString());
			}

			return false;
		} catch (IOException e) {
			log.error(e);
			// resultCommand.append("IOException");
			if (command.isSave()) {
				SaveConfig.writeToFile(pathToSave, device, command, resultCommand.toString());
			}

			return false;
		}
		
		
		//System.out.println(tempSB);
		//resultCommand.append(tempSB);
		
		//outConsole.sendMessage(resultCommand);
		//outConsole.setStatusTaskComplited("End OK");
		//outConsole.setStatusTaskComplitedLogging("OK");
		
		
		if (command.isSave()) {
			//System.out.println("pathToSave =" + pathToSave);
			SaveConfig.writeToFile(pathToSave, device, command, resultCommand.toString());
		}

		return true;
	}

	public Map<String, String> getMoreMap() {
		return moreMap;
	}

	public void setMoreMap(Map<String, String> moreMap) {
		this.moreMap = moreMap;
	}

	@Override
	public List<More> getMoreList() {

		return this.moreList;
	}

	public boolean doCommandStart(List<Command> commands, Device device) {
		for (Command command : commands) {
			if (command.getOrder() <= 50) {
				if (this.command(device, command) == false) {

					String outcommand = this.getCommand();
					outConsole.sendMessage(outcommand + "\n");

					return false;
				}
				String outcommand = this.getCommand();
				outConsole.sendMessage(outcommand + "\n");

			}

		}
		return true;
	}

	public boolean doCommandBody(List<Command> commands, Device device) {
		//System.out.println("test2");
		for (Command command : commands) {
			//System.out.println("test3");
			if ((command.getOrder() > 50) && (command.getOrder() <= 100)) {
				//System.out.println("test4");
				if (this.command(device, command) == false) {
					//System.out.println("test5");
					String outcommand = this.getCommand();

					outConsole.sendMessage(outcommand + "\n");
					return false;

				}
				String outcommand = this.getCommand();
				outConsole.sendMessage(outcommand + "\n");
			}
		}
		return true;
	};

	public boolean doCommandEnd(List<Command> commands, Device device) {
		for (Command command : commands) {
			if ((command.getOrder() > 100)) {
				if (this.command(device, command) == false) {

					String outcommand = this.getCommand();
					outConsole.sendMessage(outcommand + "\n");
					return false;
				}
				String outcommand = this.getCommand();
				outConsole.sendMessage(outcommand + "\n");
			}
		}
		return true;
	};

	public void doAllCommand(Device device) {
		outConsole.setStatusTaskComplitedLogging("OK");
		List<Command> commands = deviceRepository.getCommandToDo(device);
		System.out.println("test");
		this.outConsole.sendMessage("\nstart device " + device.getDeviceIp() + "\n");
			this.doCommandBody(commands, device);
		
		if (outConsole.getStatusTaskComplitedLogging() == "OK") {
			outConsole.writeLog("Device Name:" + device.getDeviceName() + "Device IP:" + device.getDeviceIp(), "OK");
		}

	}

	public void setPathTosave(String savePath) {
		this.pathToSave = savePath;
		
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
