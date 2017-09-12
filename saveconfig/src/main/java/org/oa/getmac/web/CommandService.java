/*
 * Service controller for commands
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Command;
import org.oa.getmac.modelTDO.DTOCommand;
import org.oa.getmac.repository.CommandRepository;
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
@RequestMapping("/api/command")
public class CommandService {
	private static Logger log = Logger.getLogger(CommandService.class);

	@Autowired
	private CommandRepository commandRepository;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "startfortemplate/{idTemplate}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOCommand> getStartForTemplate(@PathVariable int idTemplate) {
		List<DTOCommand> dtoCommands = new ArrayList<>();
		List<Command> commands = commandRepository.findStartForTemplate(idTemplate);
		for (Command command : commands) {
			dtoCommands.add(command.getDTOCommand());
		}
		return dtoCommands;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "bodyfortemplate/{idTemplate}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOCommand> getBodyForTemplate(@PathVariable int idTemplate) {
		List<DTOCommand> dtoCommands = new ArrayList<>();
		List<Command> commands = commandRepository.findBodyForTemplate(idTemplate);
		for (Command command : commands) {
			dtoCommands.add(command.getDTOCommand());
		}
		return dtoCommands;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "endfortemplate/{idTemplate}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOCommand> getEndForTemplate(@PathVariable int idTemplate) {
		List<DTOCommand> dtoCommands = new ArrayList<>();
		List<Command> commands = commandRepository.findEndForTemplate(idTemplate);
		for (Command command : commands) {
			dtoCommands.add(command.getDTOCommand());
		}
		return dtoCommands;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DTOCommand add(@RequestBody DTOCommand dtoCommand) {
		Command command = new Command();
		command.setDTOCommand(dtoCommand);
		System.out.println(command);
		commandRepository.create(command);
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody DTOCommand update(@RequestBody DTOCommand dtoCommand) {
		Command command = new Command();
		command.setDTOCommand(dtoCommand);
		System.out.println(command);
		commandRepository.update(command);
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String delete(@PathVariable int id) {
		Command command = new Command();
		command.setId(id);
		commandRepository.delete(command);
		return "OK";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/deleteForTemplate/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String deleteForTemplate(@PathVariable int id) {
		System.out.println("delete" + id);
		commandRepository.deleteForTemplate(id);
		return "OK";
	}

}
