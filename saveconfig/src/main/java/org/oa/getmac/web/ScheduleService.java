/*
 * Service controller for schedule
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.DeviceGroup;
import org.oa.getmac.model.Schedule;
import org.oa.getmac.modelTDO.DTOCommand;
import org.oa.getmac.modelTDO.DTODevice;
import org.oa.getmac.modelTDO.DTODeviceGroup;
import org.oa.getmac.modelTDO.DTOSchedule;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.ScheduleRepository;
import org.oa.getmac.repository.TaskGroupHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/schedule")
public class ScheduleService {
	private static Logger log = Logger.getLogger(ScheduleService.class);

	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private TaskGroupHostRepository taskGroupHostRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Schedule> getAll() {
		List<Schedule> schedules = scheduleRepository.findAll();
		return schedules;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "getbytskid/{idTask}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Schedule> getSchedule(@PathVariable int idTask) {
		List<Schedule> schedules = scheduleRepository.findByTaskId(idTask);
		return schedules;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String delete(@PathVariable int id) {
		Schedule schedule = new Schedule();
		schedule.setScheduleId(id);
		System.out.println("delete" + schedule);
		scheduleRepository.delete(schedule);
		return "OK";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody DTOSchedule update(@RequestBody DTOSchedule dtoSchedule) {
		Schedule schedule = new Schedule();
		schedule.setDTOSchedule(dtoSchedule);
		System.out.println(dtoSchedule);
		scheduleRepository.update(schedule);
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DTOCommand add(@RequestBody DTOSchedule dtoSchedule) {
		Schedule schedule = new Schedule();
		schedule.setDTOSchedule(dtoSchedule);
		scheduleRepository.create(schedule);
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DeviceGroup> getGroupForTaskExclude(@PathVariable int id) {
		List<DeviceGroup> exclideGroups = deviceRepository.findAllGroup();
		exclideGroups.removeAll(taskGroupHostRepository.findGroupByTaskId(id));

		return exclideGroups;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/groupsfortask/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DeviceGroup> getGroupForTaskInclude(@PathVariable int id) {
		return taskGroupHostRepository.findGroupByTaskId(id);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getallgroups", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTODeviceGroup> getAllGroups() {
		List<DTODeviceGroup> dtoDeviceGroups = new ArrayList<>();
		List<DeviceGroup> allGroups = deviceRepository.findAllGroup();
		for (DeviceGroup deviceGroup : allGroups) {
			dtoDeviceGroups.add(deviceGroup.getDTODeviceGroup());
		}
		return dtoDeviceGroups;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getlistdeviceforgroup/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTODevice> getDeviceForGroup(@PathVariable int id) {
		List<DTODevice> dtoDevices = new ArrayList<>();
		List<Device> devices = deviceRepository.findByGroupId(id);
		for (Device device : devices) {
			dtoDevices.add(device.getDeviceDTOforList());
		}
		return dtoDevices;
	}
}
