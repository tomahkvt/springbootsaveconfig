package org.oa.getmac.shell;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;
import org.apache.commons.net.telnet.SimpleOptionHandler;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;

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

public class TerminalTelnet implements Connection {

	private static final long TIMEOUT_EXECUTE = 5000; // ms
	private TerminalServer terminalServer;
	private Session session;
	// private Channel channel;
	private InputStream inStream;
	private OutputStream outStream;
	private static Logger log = Logger.getLogger(TerminalTelnet.class);
	private StringBuilder resultConnection;
	private StringBuilder resultDisconnect;
	private StringBuilder resultCommand;
	private Map<String, String> moreMap = new HashMap<>();
	private List<More> moreList;
	private OutConsole outConsole;
	private String pathToSave;
	private Device device;
	private TelnetClient tc = null;

	public TerminalTelnet() {

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
		return true;
	};
	public boolean connect(Command command) {
		resultCommand = new StringBuilder();

		resultConnection = new StringBuilder();
		String message;
		
		try {

			tc = new TelnetClient();
			TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
			EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
			SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);
			tc.addOptionHandler(ttopt);
			tc.addOptionHandler(echoopt);
			tc.addOptionHandler(gaopt);

			tc.connect(device.getDeviceIp(), 23);
			tc.getOutputStream();
			tc.getInputStream();

			inStream = new BufferedInputStream(tc.getInputStream(), 20000);
			outStream = tc.getOutputStream();

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
				if (-1 != resultConnection.indexOf(command.getWaitFor())) {
					break;
				}
				
			}
			//resultConnection.append(resultConnection);
		} catch (InvalidTelnetOptionException e) {
			resultConnection.append("\nInvalidTelnetOption\n");
			message = "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
			message += " InvalidTelnetOption";
			resultConnection.append(message);
			resultDisconnect = new StringBuilder(message);
			this.outConsole.sendMessage(message);
			this.outConsole.writeLog(message, "Error");
			this.outConsole.setStatusTaskComplited("End error");
			this.outConsole.setStatusTaskComplitedLogging("Error");
			return false;
			
		} catch (SocketException e) {
			resultConnection.append("\nError Connect Socket port 23\n");
			message = "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
			message += " Error Connect Socket port 23";
			resultConnection.append(message);
			resultDisconnect = new StringBuilder(message);
			this.outConsole.sendMessage(message);
			this.outConsole.writeLog(message, "Error");
			this.outConsole.setStatusTaskComplited("End error");
			this.outConsole.setStatusTaskComplitedLogging("Error");
			return false;
			
		} catch (IOException e) {
			resultConnection.append("\nError IOException\n");
			message = "Device: " + device.getDeviceName();
			message += " IP:  " + device.getDeviceIp();
			message += " Error IOException";
			resultConnection.append(message);
			resultDisconnect = new StringBuilder(message);
			this.outConsole.sendMessage(message);
			this.outConsole.writeLog(message, "Error");
			this.outConsole.setStatusTaskComplited("End error");
			this.outConsole.setStatusTaskComplitedLogging("Error");
			return false;
		}
		catch (CommandTimeOut e) {
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
			return false;
		}

		message = "\nConnected to Device: " + device.getDeviceName();
		message += " IP:  " + device.getDeviceIp();
		// log.info(message);
		resultConnection.append(message);
		resultDisconnect = new StringBuilder(message);
		this.outConsole.sendMessage(message);
		this.outConsole.writeLog(message, "OK");
		// System.out.println(resultConnection.toString());
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
					// resultCommand.append(tempSB);
					// log.info(resultCommand);
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
			// System.out.println(tempSB);

		} catch (CommandTimeOut e) {
			// System.out.println(tempSB);
			resultCommand.append(tempSB);
			String comment = "Device IP:" + device.getDeviceIp() + " Timeout promt wait for:" + command.getWaitFor()
					+ " command order:" + command.getOrder();
			resultCommand.append("\n" + comment);
			outConsole.setStatusTaskComplited("End error");
			outConsole.setStatusTaskComplitedLogging("Error");
			outConsole.writeLog(comment, "Error");
			log.error(e);
			if (command.isSave()) {
				SaveConfig.writeToFile(pathToSave, device, command, resultCommand.toString());
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

		// System.out.println(tempSB);
		// resultCommand.append(tempSB);

		// outConsole.sendMessage(resultCommand);
		// outConsole.setStatusTaskComplited("End OK");
		// outConsole.setStatusTaskComplitedLogging("OK");

		if (command.isSave()) {
			// System.out.println("pathToSave =" + pathToSave);
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

				if (command.getCommand().equals("{telnet}") == true) {
					if (this.connect(command) == false){
						String outcommand = this.getConnect();
						outConsole.sendMessage(outcommand + "\n");
						return false;
					}else{
						String outcommand = this.getConnect();
						outConsole.sendMessage(outcommand + "\n");
					}
				} else {
					if (this.command(device, command) == false) {
						String outcommand = this.getCommand();
						outConsole.sendMessage(outcommand + "\n");
						return false;
					}
				}

				String outcommand = this.getCommand();
				outConsole.sendMessage(outcommand + "\n");

			}

		}
		return true;
	}

	public boolean doCommandBody(List<Command> commands, Device device) {
		// System.out.println("test2");
		for (Command command : commands) {
			// System.out.println("test3");
			if ((command.getOrder() > 50) && (command.getOrder() <= 100)) {
				// System.out.println("test4");
				if (this.command(device, command) == false) {
					// System.out.println("test5");
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
		// System.out.println("test");
		this.outConsole.sendMessage("\nstart device " + device.getDeviceIp() + "\n");
		if (this.doCommandStart(commands, device) == true) {
			if (this.doCommandBody(commands, device) == true) {
				if (this.doCommandEnd(commands, device) == false) {
					// outConsole.sendMessage("Disconect Connect\n");
					this.disconnect();
					this.connect();
				}

			} else {
				// outConsole.sendMessage("Disconect Connect\n");
				this.disconnect();
				//this.connect();
			}

		} else {
			this.disconnect();
			/*
			if (this.doCommandEnd(commands, device) == false) {
				// outConsole.sendMessage("End Disconect Connect\n");
				//this.disconnect();
				//this.connect();
			}
			*/
		}
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
