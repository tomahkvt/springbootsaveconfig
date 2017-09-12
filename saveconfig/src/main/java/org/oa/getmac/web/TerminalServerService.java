/*
 * Controller service for terminalserver
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.JsonResponse;
import org.oa.getmac.model.Template;
import org.oa.getmac.model.TerminalServer;
import org.oa.getmac.modelTDO.DTOTerminalServer;
import org.oa.getmac.repository.TerminalServerRepository;
import org.oa.getmac.shell.TerminalServerConnection;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/api/terminalserver")
public class TerminalServerService {
	private static Logger log = Logger.getLogger(TerminalServerService.class);

	@Autowired
	private TerminalServerRepository terminalServerRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TerminalServer> getAll() {

		List<TerminalServer> terminalServers = terminalServerRepository.findAll();
		return terminalServers;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOTerminalServer> getAllDTO() {
		List<DTOTerminalServer> dtoTemplates = new ArrayList<>();
		List<TerminalServer> terminalServers = terminalServerRepository.findAll();
		for (TerminalServer terminalServer : terminalServers) {
			dtoTemplates.add(terminalServer.getDTOTerminalServerforList());
		}
		return dtoTemplates;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getDTO/{idTerminalServer}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DTOTerminalServer getDTOTerminalServerforList(@PathVariable int idTerminalServer) {
		TerminalServer terminalServer = terminalServerRepository.findById(idTerminalServer);
		return terminalServer.getDTOTerminalServer();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody JsonResponse delete(@PathVariable int id) {
		JsonResponse jsonResponse = new JsonResponse();
		String result;
		System.out.println("delete");
		TerminalServer terminalServer = terminalServerRepository.findById(id);
		if (terminalServer != null) {
			if (terminalServer.getDeviceName().equals("ssh") == true) {
				result = "Terminal server with name " + terminalServer.getDeviceName() + " can't be delete";
				jsonResponse.setStatus("FAIL");
				jsonResponse.setResult(result);
				return jsonResponse;
			}
			if (terminalServer.getDeviceName().equals("telnet") == true) {
				result = "Terminal server with name " + terminalServer.getDeviceName() + " can't be delete";
				jsonResponse.setStatus("FAIL");
				jsonResponse.setResult(result);
				return jsonResponse;
			}

			List<Template> templates = terminalServerRepository.delete(terminalServer);
			if (!templates.isEmpty()) {
				jsonResponse.setStatus("FAIL");
				result = "terminalserver with id" + terminalServer.getId() + " and name"
						+ terminalServer.getDeviceName() + "use in Template: <BR>";
				for (Template template : templates) {
					result = result + template.getTemplateName() + "<br>";
				}
				jsonResponse.setResult(result);
			} else {

				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setResult("terminalserver with id" + terminalServer.getId() + " and name"
						+ terminalServer.getDeviceName() + "is deleted");

			}
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult("terminalserver with id " + id + "in not exists");
		}
		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse add(@RequestBody @Valid DTOTerminalServer dtoTerminalServer,
			BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		TerminalServer terminalServer = new TerminalServer();
		terminalServer.setDTOTerminalServer(dtoTerminalServer);
		if (!result.hasErrors()) {
			if (terminalServerRepository.isExistByName(terminalServer)) {
				result.rejectValue("deviceName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			terminalServerRepository.create(terminalServer);
			int createId = terminalServer.getId();
			terminalServer = terminalServerRepository.findById(createId);
			jsonResponse.setResult(terminalServer.getDTOTerminalServer());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse update(@RequestBody @Valid DTOTerminalServer dtoTerminalServer,
			BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		TerminalServer terminalServer = new TerminalServer();
		terminalServer.setDTOTerminalServer(dtoTerminalServer);

		if (!result.hasErrors()) {
			if (terminalServerRepository.isExistByName(terminalServer)) {
				result.rejectValue("deviceName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			terminalServerRepository.update(terminalServer);
			terminalServer = terminalServerRepository.findById(dtoTerminalServer.getId());

			jsonResponse.setResult(terminalServer.getDTOTerminalServer());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/clone", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse clone(@RequestBody @Valid DTOTerminalServer dtoTerminalServer,
			BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		TerminalServer terminalServer = new TerminalServer();
		terminalServer.setDTOTerminalServer(dtoTerminalServer);
		terminalServer.setId(0);

		if (!result.hasErrors()) {
			if (terminalServerRepository.isExistByName(terminalServer)) {
				result.rejectValue("deviceName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			terminalServer = terminalServerRepository.findById(dtoTerminalServer.getId());

			terminalServerRepository.create(terminalServer);
			dtoTerminalServer.setId(terminalServer.getId());
			terminalServer.setDTOTerminalServer(dtoTerminalServer);
			terminalServerRepository.update(terminalServer);

			// terminalServer =
			// terminalServerRepository.findById(dtoTerminalServer.getId());

			jsonResponse.setResult(terminalServer.getDTOTerminalServer());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/comparepassword1", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse comparepassword1(@RequestBody DTOTerminalServer dtoTerminalServer,
			BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		TerminalServer terminalServer;
		ValidationUtils.rejectIfEmpty(result, "passw1", "Password 1 is Empty");
		if (!result.hasErrors()) {
			terminalServer = terminalServerRepository.findById(dtoTerminalServer.getId());
			if (terminalServer.getPassw1().equals(dtoTerminalServer.getPassw1())) {
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
	@RequestMapping(value = "/comparepassword2", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse comparepassword2(@RequestBody DTOTerminalServer dtoTerminalServer,
			BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		TerminalServer terminalServer;
		ValidationUtils.rejectIfEmpty(result, "passw2", "Password 2 is Empty");
		if (!result.hasErrors()) {
			terminalServer = terminalServerRepository.findById(dtoTerminalServer.getId());
			if (terminalServer.getPassw2().equals(dtoTerminalServer.getPassw2())) {
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
	@RequestMapping(value = "/testTerminalServer/{idTerminalServer}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String TestTerminalServer(@PathVariable int idTerminalServer) {
		TestOutConsole testOutConsole = new TestOutConsole();
		TerminalServer terminalServer;
		terminalServer = terminalServerRepository.findById(idTerminalServer);

		testOutConsole.sendMessage("Test of server id : " + idTerminalServer + "\n");
		testOutConsole.sendMessage("Server name : " + terminalServer.getDeviceName() + "\n");
		testOutConsole.sendMessage("Server IP : " + terminalServer.getDeviceIp() + "\n");
		testOutConsole.sendMessage("Is rechable icmp : ");
		boolean isReachableIcmp = terminalServer.isReachableIcmp();
		testOutConsole.sendMessage(isReachableIcmp + "\n");
		System.out.println("After icmp");
		if (isReachableIcmp == false) {
			return testOutConsole.getResult().toString();
		}
		testOutConsole.sendMessage("Is rechable tcp port 22 : ");
		boolean isReachableTcp = terminalServer.isReachableTcp();
		System.out.println("After tcp" + isReachableTcp);
		testOutConsole.sendMessage(isReachableTcp + "\n");
		if (isReachableTcp == false) {
			return testOutConsole.getResult().toString();
		}
		System.out.println("After setOutConsole");
		TerminalServerConnection terminalServerConnection = new TerminalServerConnection();
		terminalServerConnection.setTerminalServer(terminalServer);
		terminalServerConnection.setOutConsole(testOutConsole);

		terminalServerConnection.connect();
		System.out.println("After connect");
		terminalServerConnection.disconnect();
		System.out.println("After disconnect");
		return testOutConsole.getResult().toString();
	}
}
