/*
 * Service controller for Devices
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.DeviceGroup;
import org.oa.getmac.model.GlobalParam;
import org.oa.getmac.model.JsonResponse;
import org.oa.getmac.model.Template;
import org.oa.getmac.model.TerminalServer;
import org.oa.getmac.modelTDO.DTODevice;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.MoreRepository;
import org.oa.getmac.shell.Connection;
import org.oa.getmac.shell.OutConsole;
import org.oa.getmac.shell.TerminalServerConnection;
import org.oa.getmac.shell.TerminalSsh;
import org.oa.getmac.shell.TerminalTelnet;
import org.oa.getmac.shell.TestOutConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/api/device")
public class DeviceService {
	private static Logger log = Logger.getLogger(DeviceService.class);
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private MoreRepository moreRepository;
	@Autowired
	private GlobalParam globalParam;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "getDTO/{idDevice}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DTODevice getDTODevice(@PathVariable int idDevice) {
		log.info("View Device with id=" + idDevice);
		Device device = deviceRepository.findById(idDevice);
		return device.getDTODevice();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Device> getAll() {
		List<Device> devices = deviceRepository.findAll();
		return devices;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/tableDTO", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTODevice> getAllDTO() {
		List<DTODevice> devicesDTO = new ArrayList<>();
		List<Device> devices = deviceRepository.findAll();
		for (Device device : devices) {
			devicesDTO.add(device.getDeviceDTOforTable());
		}
		return devicesDTO;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/groups", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DeviceGroup> getAllGroup() {

		return deviceRepository.findAllGroup();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/test2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Device> getAllDTOTest() {
		List<Device> devices = deviceRepository.findAllDto();
		return devices;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse add(@RequestBody @Valid DTODevice dtoDevice, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();
		Device device = new Device();
		device.setDTODevice(dtoDevice);
		System.out.println(dtoDevice);

		if (!result.hasErrors()) {
			if (deviceRepository.isExistByName(device)) {
				result.rejectValue("deviceName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			deviceRepository.create(device);
			device = deviceRepository.findById(device.getId());
			jsonResponse.setResult(device.getDeviceDTOforTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());
		}

		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse update(@RequestBody @Valid DTODevice dtoDevice, BindingResult result) {

		System.out.println(dtoDevice);
		JsonResponse jsonResponse = new JsonResponse();
		Template template = new Template();
		template.setId(dtoDevice.getTemplate().getId());
		Device device = new Device();
		device.setDTODevice(dtoDevice);
		System.out.println(device);

		if (!result.hasErrors()) {
			if (deviceRepository.isExistByName(device)) {
				result.rejectValue("deviceName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			deviceRepository.update(device);
			device = deviceRepository.findById(dtoDevice.getId());
			jsonResponse.setResult(device.getDeviceDTOforTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String delete(@PathVariable int id) {
		Device device = new Device();
		device.setId(id);
		deviceRepository.delete(device);
		return "OK";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/testDevice/{idDevice}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String TestDevice(@PathVariable int idDevice, Locale loc, String code,
			HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");

		TestOutConsole testOutConsole = new TestOutConsole();
		OutConsole outConsole = testOutConsole;
		Device device = deviceRepository.findById(idDevice);
		TerminalServer terminalServer = device.getTemplate().getTerminalServer();
		Connection connection = null;
		boolean isConnectedtoTerminalServer = false;
		boolean isReachableIcmp = false;
		boolean isReachableTcp = false;
		
		switch (terminalServer.getDeviceName()) {
		case "telnet":

			outConsole.sendMessage("Test of server id : " + terminalServer.getId() + "\n");
			outConsole.sendMessage("Server name : " + terminalServer.getDeviceName() + "\n");
			outConsole.sendMessage("Server IP : " + terminalServer.getDeviceIp() + "\n");
			outConsole.sendMessage("Is rechable icmp : ");
			isReachableIcmp = terminalServer.isReachableIcmp();
			outConsole.sendMessage(isReachableIcmp + "\n");
			if (!isReachableIcmp) {
				return outConsole.getResult().toString();
			}
			outConsole.sendMessage("Is rechable tcp port 22 : ");

			isReachableTcp = terminalServer.isReachableTcp();
			outConsole.sendMessage(isReachableTcp + "\n");
			if (!isReachableTcp) {
				return outConsole.getResult().toString();
			}
			
			TerminalTelnet terminalTelnet = new TerminalTelnet();
			terminalTelnet.setPathTosave(globalParam.getSavePath());
			terminalTelnet.setOutConsole(outConsole);
			terminalTelnet.setTerminalServer(terminalServer);
			terminalTelnet.setDeviceRepository(deviceRepository);
			terminalTelnet.setDevice(device);
			connection = terminalTelnet;
			connection.setMoreList(moreRepository.getMoreToDo(device.getTemplate().getId()));
			connection.doAllCommand(device);
			return outConsole.getResult().toString();
			

		case "ssh":

			outConsole.sendMessage("Test of server id : " + terminalServer.getId() + "\n");
			outConsole.sendMessage("Server name : " + terminalServer.getDeviceName() + "\n");
			outConsole.sendMessage("Server IP : " + terminalServer.getDeviceIp() + "\n");
			outConsole.sendMessage("Is rechable icmp : ");
			isReachableIcmp = terminalServer.isReachableIcmp();
			outConsole.sendMessage(isReachableIcmp + "\n");
			if (!isReachableIcmp) {
				return outConsole.getResult().toString();
			}
			outConsole.sendMessage("Is rechable tcp port 22 : ");

			isReachableTcp = terminalServer.isReachableTcp();
			outConsole.sendMessage(isReachableTcp + "\n");
			if (!isReachableTcp) {
				return outConsole.getResult().toString();
			}
			
			TerminalSsh terminalSsh = new TerminalSsh();
			terminalSsh.setPathTosave(globalParam.getSavePath());
			terminalSsh.setOutConsole(outConsole);
			terminalSsh.setTerminalServer(terminalServer);
			terminalSsh.setDeviceRepository(deviceRepository);
			terminalSsh.setDevice(device);
			connection = terminalSsh;

			isConnectedtoTerminalServer = connection.connect();
			if (!isConnectedtoTerminalServer) {
				outConsole.writeLog("Device: " + device.getDeviceIp() + " is not connected", "Error");
				
			}

			connection.setMoreList(moreRepository.getMoreToDo(device.getTemplate().getId()));
			connection.doAllCommand(device);
			connection.disconnect();

			return outConsole.getResult().toString();
			

		default:
			outConsole.sendMessage("Test of server id : " + terminalServer.getId() + "\n");
			outConsole.sendMessage("Server name : " + terminalServer.getDeviceName() + "\n");
			outConsole.sendMessage("Server IP : " + terminalServer.getDeviceIp() + "\n");
			outConsole.sendMessage("Is rechable icmp : ");
			isReachableIcmp = terminalServer.isReachableIcmp();
			outConsole.sendMessage(isReachableIcmp + "\n");
			if (!isReachableIcmp) {
				return outConsole.getResult().toString();
			}
			outConsole.sendMessage("Is rechable tcp port 22 : ");

			isReachableTcp = terminalServer.isReachableTcp();
			outConsole.sendMessage(isReachableTcp + "\n");
			if (!isReachableTcp) {
				return outConsole.getResult().toString();
			}
			TerminalServerConnection terminalServerConnection = new TerminalServerConnection();
			terminalServerConnection.setTerminalServer(terminalServer);
			terminalServerConnection.setPathTosave(globalParam.getSavePath());
			terminalServerConnection.setMoreList(moreRepository.getMoreToDo(device.getTemplate().getId()));
			terminalServerConnection.setOutConsole(outConsole);
			terminalServerConnection.setDeviceRepository(deviceRepository);
			connection = terminalServerConnection;
			isConnectedtoTerminalServer = terminalServerConnection.connect();
			outConsole.sendMessage("Connect " + isConnectedtoTerminalServer + " \n");
			if (!isConnectedtoTerminalServer) {
				return outConsole.getResult().toString();
			}
			outConsole.sendMessage(terminalServerConnection.getConnect());
			terminalServerConnection.doAllCommand(device);

			connection.disconnect();

			return outConsole.getResult().toString();
		}
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/comparepassword/{numberPassword}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse comparepassword(@PathVariable int numberPassword,
			@RequestBody DTODevice dtoDevice, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		Device device;
		ValidationUtils.rejectIfEmpty(result, "password" + numberPassword, "Password " + numberPassword + " is Empty");
		if (!result.hasErrors()) {
			device = deviceRepository.findById(dtoDevice.getId());
			Boolean PasswordIsEquel = false;
			switch (numberPassword) {
			case 1:
				PasswordIsEquel = device.getPassword1().equals(dtoDevice.getPassword1());
				break;
			case 2:
				PasswordIsEquel = device.getPassword2().equals(dtoDevice.getPassword2());
				break;
			case 3:
				PasswordIsEquel = device.getPassword3().equals(dtoDevice.getPassword3());
				break;
			case 4:
				PasswordIsEquel = device.getPassword4().equals(dtoDevice.getPassword4());
				break;

			default:
				break;
			}

			if (PasswordIsEquel == true) {
				jsonResponse.setStatus("equal");
			} else {
				jsonResponse.setStatus("not equal");
			}
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());
		}
		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/clone", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse clone(@RequestBody @Valid DTODevice dtoDevice, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		Device device = new Device();
		device.setDTODevice(dtoDevice);
		device.setId(0);

		if (!result.hasErrors()) {
			if (deviceRepository.isExistByName(device)) {
				result.rejectValue("deviceName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			device = deviceRepository.findById(dtoDevice.getId());
			device.setId(0);
			deviceRepository.create(device);
			dtoDevice.setId(device.getId());
			device.setDTODevice(dtoDevice);
			deviceRepository.update(device);
			device = deviceRepository.findById(device.getId());
			jsonResponse.setResult(device.getDeviceDTOforTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());
		}
		return jsonResponse;
	}
}
