/*
 * Service controller 
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.JsonResponse;
import org.oa.getmac.model.Template;
import org.oa.getmac.modelTDO.DTOTemplate;
import org.oa.getmac.repository.CommandRepository;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.MoreRepository;
import org.oa.getmac.repository.TemplateRepository;
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
@RequestMapping("/api/template")
public class TemplateService {
	private static Logger log = Logger.getLogger(TemplateService.class);

	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private CommandRepository commandRepository;

	@Autowired
	private MoreRepository moreRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Template> getAll() {
		List<Template> templates = templateRepository.findAll();
		return templates;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOTemplate> getAllDTO() {
		List<DTOTemplate> dtoTemplates = new ArrayList<>();
		List<Template> templates = templateRepository.findAll();
		for (Template template : templates) {
			dtoTemplates.add(template.getDTOTemplateForList());
		}
		return dtoTemplates;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/tableDTO", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOTemplate> listDTOTemplate() {
		List<DTOTemplate> dtoTempaltes = new ArrayList<DTOTemplate>();
		List<Template> tempaltes = templateRepository.findAll();

		for (Template tempalte : tempaltes) {
			dtoTempaltes.add(tempalte.getDTOTemplateForTable());
		}

		return dtoTempaltes;

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "getDTO/{idTemplate}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DTOTemplate getDTOTemplate(@PathVariable int idTemplate) {
		// log.info("View Device with id=" + idDevice);
		Template template = templateRepository.findById(idTemplate);
		return template.getDTOTemplate();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse add(@RequestBody @Valid DTOTemplate dtoTemplate, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		Template template = new Template();
		template.setDTOTemplate(dtoTemplate);
		if (!result.hasErrors()) {
			if (templateRepository.isExistByName(template)) {
				result.rejectValue("templateName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			templateRepository.create(template);
			int createId = template.getId();
			template = templateRepository.findById(createId);
			jsonResponse.setResult(template.getDTOTemplateForTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse update(@RequestBody @Valid DTOTemplate dtoTemplate, BindingResult result) {

		JsonResponse jsonResponse = new JsonResponse();

		Template template = new Template();
		template.setDTOTemplate(dtoTemplate);

		if (!result.hasErrors()) {
			if (templateRepository.isExistByName(template)) {
				result.rejectValue("templateName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			templateRepository.update(template);
			template = templateRepository.findById(dtoTemplate.getId());
			jsonResponse.setResult(template.getDTOTemplateForTable());
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
		String result;

		Template template = templateRepository.findById(id);
		if (template != null) {
			List<Device> devices = deviceRepository.findByIdTemplate(template);
			if (!devices.isEmpty()) {
				jsonResponse.setStatus("FAIL");
				result = "Template with id:" + template.getId() + " and name:" + template.getTemplateName()
						+ " use in Device: <BR>";
				for (Device device : devices) {
					result = result + device.getDeviceName() + "<br>";
				}
				jsonResponse.setResult(result);
			} else {
				templateRepository.delete(template);
				commandRepository.deleteForTemplate(template.getId());
				moreRepository.deleteForTemplate(template.getId());
				jsonResponse.setStatus("SUCCESS");
				jsonResponse.setResult("tempalte with id:" + template.getId() + " and name:"
						+ template.getTemplateName() + " is deleted");

			}
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult("terminalserver with id " + id + "in not exists");
		}
		return jsonResponse;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/clone", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse clone(@RequestBody @Valid DTOTemplate dtoTemplate, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		Template template = new Template();
		template.setDTOTemplate(dtoTemplate);
		template.setId(0);

		System.out.println(templateRepository.isExistByName(template));

		if (!result.hasErrors()) {
			if (templateRepository.isExistByName(template)) {
				result.rejectValue("templateName", "is exists", "is exists");
			}
		}

		if (!result.hasErrors()) {
			jsonResponse.setStatus("SUCCESS");
			templateRepository.create(template);
			template = templateRepository.findById(template.getId());
			jsonResponse.setResult(template.getDTOTemplateForTable());
		} else {
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result.getAllErrors());

		}
		return jsonResponse;
	}
}
