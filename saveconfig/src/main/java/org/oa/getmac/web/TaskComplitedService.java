/*
 * Service controller for TaskComplited
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.TaskComplited;
import org.oa.getmac.modelTDO.DTODataTable;
import org.oa.getmac.repository.TaskComplitedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/api/taskcomplited")
public class TaskComplitedService {
	private static Logger log = Logger.getLogger(TaskComplitedService.class);

	@Autowired
	private TaskComplitedRepository taskComplitedRepository;

	@RequestMapping(value = "/task/{idTask}",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TaskComplited> getTask(@PathVariable int idTask) {
		return taskComplitedRepository.findByTaskId(idTask);
	}

	@RequestMapping(value = "/jointask",method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	public @ResponseBody DTODataTable getJoinTask(HttpServletRequest request) {
		return taskComplitedRepository.findJoinTaskByTaskId(request);
	}
}
