/*
 * Service Controller for Task
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.JsonResponse;
import org.oa.getmac.model.Task;
import org.oa.getmac.modelTDO.DTOTask;
import org.oa.getmac.repository.ScheduleRepository;
import org.oa.getmac.repository.TaskRepository;
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
@RequestMapping("/api/task")
public class TaskService {
	private static Logger log = Logger.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Task> getAll() {
		List<Task> tasks = taskRepository.findAll();
		return tasks;

	}

	@RequestMapping(value = "/fortable", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOTask> getAllForTable() {
		List<DTOTask> dtoTasks = new ArrayList<>();
		List<Task> tasks = taskRepository.findAll();
		for (Task task : tasks) {
			dtoTasks.add(task.getDTOTaskForTable());
		}

		return dtoTasks;
	}

	@RequestMapping(value = "/task/{idTask}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DTOTask getTask(@PathVariable int idTask) {
		return taskRepository.findById(idTask).getDTOTask();

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse add(@RequestBody @Valid DTOTask dtoTask, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();
		Task task = new Task();
		task.setDTOTask(dtoTask);

		if (!result.hasErrors()) {
			if (taskRepository.isExistByName(task)) {
				result.rejectValue("name", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			taskRepository.create(task);
			task = taskRepository.findById(task.getTaskid());
			jsonResponse.setResult(task.getDTOTaskForTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}

		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse update(@RequestBody @Valid DTOTask dtoTask, BindingResult result) {

		JsonResponse jsonResponse = new JsonResponse();

		Task task = new Task();
		task.setDTOTask(dtoTask);
		System.out.println(dtoTask);
		System.out.println(task);

		if (!result.hasErrors()) {
			if (taskRepository.isExistByName(task)) {
				result.rejectValue("name", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			taskRepository.update(task);
			task = taskRepository.findById(dtoTask.getTaskid());
			jsonResponse.setResult(task.getDTOTaskForTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}

		return jsonResponse;

	}

	@Secured("ROLE_ADMIN")

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody JsonResponse delete(@PathVariable int id) {
		JsonResponse jsonResponse = new JsonResponse();

		Task task = taskRepository.findById(id);
		if (task != null) {
			taskRepository.delete(task);
			scheduleRepository.deleteForTask(id);
			jsonResponse.setStatus("SUCCESS");
			jsonResponse.setResult(
					"Task with id: " + task.getTaskid() + " and name: " + task.getName() + " is deleted");
			
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult("Task with id " + id + "in not exists");
		}
		return jsonResponse;
	}
}
