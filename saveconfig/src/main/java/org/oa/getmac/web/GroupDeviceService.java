/*
 * Service controller groupdevice
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.DeviceGroup;
import org.oa.getmac.model.JsonResponse;
import org.oa.getmac.model.Task;
import org.oa.getmac.modelTDO.DTODeviceGroup;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.GroupDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/api/groupdevice")
public class GroupDeviceService {
	private static Logger log = Logger.getLogger(GroupDeviceService.class);

	@Autowired
	private GroupDeviceRepository groupDeviceRepository;
	@Autowired
	private DeviceRepository deviceRepository;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/tableDTO", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTODeviceGroup> getAllDTO() {
		List<DTODeviceGroup> dtogroups = new ArrayList<>();
		List<DeviceGroup> groups = groupDeviceRepository.findAll();
		for (DeviceGroup group : groups) {
			dtogroups.add(group.getDTODeviceGroup());
		}
		return dtogroups;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "getDTO/{idDeviceGroup}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DTODeviceGroup getDTODevice(@PathVariable int idDeviceGroup) {

		DeviceGroup deviceGroup = groupDeviceRepository.findById(idDeviceGroup);
		return deviceGroup.getDTODeviceGroup();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse add(@RequestBody @Valid DTODeviceGroup dtoDeviceGroup, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		DeviceGroup deviceGroup = new DeviceGroup();
		deviceGroup.setDTODeviceGroup(dtoDeviceGroup);

		if (!result.hasErrors()) {
			if (groupDeviceRepository.isExistByName(deviceGroup)) {
				result.rejectValue("name", "is exists", "is exists");
			}
			if (deviceGroup.getName() == null) {
				result.rejectValue("name", "is null", "is null");
			}

		}

		if (!result.hasErrors()) {

			jsonResponse.setStatus("SUCCESS");
			groupDeviceRepository.create(deviceGroup);
			jsonResponse.setResult(deviceGroup.getDTODeviceGroup());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());
		}
		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse update(@RequestBody @Valid DTODeviceGroup dtoDeviceGroup, BindingResult result) {

		JsonResponse jsonResponse = new JsonResponse();

		DeviceGroup deviceGroup = new DeviceGroup();
		deviceGroup.setDTODeviceGroup(dtoDeviceGroup);
		// System.out.println(user);

		if (!result.hasErrors()) {
			if (groupDeviceRepository.isExistByName(deviceGroup)) {
				result.rejectValue("name", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			groupDeviceRepository.update(deviceGroup);
			deviceGroup = groupDeviceRepository.findById(dtoDeviceGroup.getGroupid());
			jsonResponse.setResult(deviceGroup.getDTODeviceGroup());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{idDeviceGroup}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody JsonResponse delete(@PathVariable int idDeviceGroup) {
		JsonResponse jsonResponse = new JsonResponse();
		String result = "";
		System.out.println("delete");
		DeviceGroup deviceGroup = groupDeviceRepository.findById(idDeviceGroup);
		if (deviceGroup != null) {

			List<Device> devices = deviceRepository.findByGroupId(idDeviceGroup);

			if (!devices.isEmpty()) {
				jsonResponse.setStatus("FAIL");
				result = "GroupDevice with id " + deviceGroup.getGroupid() + " and name " + deviceGroup.getName()
						+ " use in Device: <BR>";
				for (Device device : devices) {
					result = result + device.getDeviceName() + "<br>";
				}
				jsonResponse.setResult(result);
			}

			List<Task> tasks = groupDeviceRepository.findTaskByGroup(idDeviceGroup);

			if (!tasks.isEmpty()) {
				jsonResponse.setStatus("FAIL");
				result += "<BR>GroupDevice with id " + deviceGroup.getGroupid() + " and name " + deviceGroup.getName()
						+ " use in Task: <BR>";
				for (Task task : tasks) {
					result = result + task.getName() + "<br>";
				}
				jsonResponse.setResult(result);
			}

			if (devices.isEmpty() && tasks.isEmpty()) {
				//groupDeviceRepository.delete(deviceGroup);
				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setResult("terminalserver with id" + deviceGroup.getGroupid() + " and name"
						+ deviceGroup.getName() + "is deleted");
				
			}
			
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult("terminalserver with id " + idDeviceGroup + "in not exists");
		}
		return jsonResponse;
	}
}
