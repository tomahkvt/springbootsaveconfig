/*
 * Service controller for active log
 */

package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.process.ControlGetData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/log")
public class ActiveLogService {
	private static Logger log = Logger.getLogger(ActiveLogService.class);
	@Autowired
	private ControlGetData controlGetData;

	@RequestMapping(value = "start/{idTask}", method = RequestMethod.GET)
	public @ResponseBody void start(@PathVariable int idTask) {
		controlGetData.startmanual(idTask);
	}

	@RequestMapping(value = "stop", method = RequestMethod.GET)
	public @ResponseBody void stop() {
		log.info("Stop process");
		controlGetData.stop();
	}

	@RequestMapping(value = "pause", method = RequestMethod.GET)
	public @ResponseBody String pause() {
		controlGetData.pause();
		log.info("resume process");
		return "pause";
	}

	@RequestMapping(value = "resume", method = RequestMethod.GET)
	public @ResponseBody String resume() {
		controlGetData.resume();
		log.info("resume process");
		return "resume";
	}

	@RequestMapping(value = "getstatus", method = RequestMethod.GET)
	public @ResponseBody String getStatus() {
		return controlGetData.getStatus();
	}
}