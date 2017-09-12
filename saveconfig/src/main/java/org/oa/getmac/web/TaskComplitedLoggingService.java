/*
 * Service controller for TaskComplitedLogging
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.TaskComplitedLogging;
import org.oa.getmac.repository.TaskComplitedLoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/taskcomplitedlogging")
public class TaskComplitedLoggingService {
	private static Logger log = Logger.getLogger(TaskComplitedLoggingService.class);

	@Autowired
	private TaskComplitedLoggingRepository taskComplitedLoggingRepository;

	@RequestMapping(value = "/taskcomplited/{idTaskComplited}",method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TaskComplitedLogging> getTask(@PathVariable int idTaskComplited) {
		return taskComplitedLoggingRepository.findByTaskComplitedId(idTaskComplited);
	}
	
}
