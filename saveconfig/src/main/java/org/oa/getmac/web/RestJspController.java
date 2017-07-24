/*
 * Class main controller
 */
package org.oa.getmac.web;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.oa.getmac.modelTDO.DTOSystemParam;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.GlobalParamRepository;
import org.oa.getmac.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class RestJspController {
	private static Logger log = Logger.getLogger(RestJspController.class);

	@Autowired
	private GlobalParamRepository globalParamRepository;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private TemplateRepository templateRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		return "redirect:/systemgeneral";
	}
	 @ExceptionHandler({SQLException.class,DataAccessException.class})
	  public String databaseError() {
	    return "databaseError";
	  }
	 
	@RequestMapping(value = "Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() throws Exception {
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "systemgeneral", method = RequestMethod.GET)
	public String SystemGeneral(Model model) throws SQLException {
		Map<String, String> globalParamMap = globalParamRepository.getMap();
		DTOSystemParam dtoSystemParam = new DTOSystemParam();
		String detectSystem = System.getProperty("os.name");
		dtoSystemParam.setDetectSystem(detectSystem);
		dtoSystemParam.setLinuxSavePath((String) globalParamMap.get("PathToBackUPLinux"));
		dtoSystemParam.setWinSavePath((String) globalParamMap.get("PathToBackUPWin"));
		model.addAttribute("dtoSystemParam", dtoSystemParam);
		Long templateCount = templateRepository.getCountTempelate();
		model.addAttribute("templatecount", templateCount);
		Long deviceCount = deviceRepository.getCountDevice();
		model.addAttribute("devicecount", deviceCount);
		return "systemgeneral";
	}
	
	@RequestMapping(value = "device", method = RequestMethod.GET)
	public String restDevice(Model model) {
		return "restdevices";
	}
	
	@RequestMapping(value = "viewing", method = RequestMethod.GET)
	public String Viewing(Model model) {
		model.addAttribute("slave_menu_active", "viewing");
		return "viewing";
	}
	
	
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String restUsers(Model model) {
		model.addAttribute("top_menu_active", "menu_aministration");	
		model.addAttribute("slave_menu_show", "aministration");
		model.addAttribute("slave_menu_active", "users");
		return "users";
	}
	

	@RequestMapping(value = "groupdevice", method = RequestMethod.GET)
	public String groupdevice(Model model) {
		model.addAttribute("top_menu_active", "menu_configure");	
		model.addAttribute("slave_menu_show", "configure");
		model.addAttribute("slave_menu_active", "groupdevice");
		return "groupdevice";
	}

	
	@RequestMapping(value = "devicetable", method = RequestMethod.GET)
	public String deviceTable(Model model) {
		model.addAttribute("top_menu_active", "menu_configure");	
		model.addAttribute("slave_menu_show", "configure");
		model.addAttribute("slave_menu_active", "devicetable");
		return "devicetable";
	}

	@RequestMapping(value = "template", method = RequestMethod.GET)
	public String restTempalte(Model model) {
		model.addAttribute("top_menu_active", "menu_configure");	
		model.addAttribute("slave_menu_show", "configure");
		model.addAttribute("slave_menu_active", "template");
		return "tempaltetable";
	}

	@RequestMapping(value = "terminalserver", method = RequestMethod.GET)
	public String restterminalServer(Model model) {
		model.addAttribute("top_menu_active", "menu_configure");	
		model.addAttribute("slave_menu_show", "configure");
		model.addAttribute("slave_menu_active", "terminalserver");
		return "terminalservertable";
	}

	@RequestMapping(value = "terminalserver", params = "id", method = RequestMethod.GET)
	public String restterminalServerWithId(Model model,
					@RequestParam(value = "id") long id) {
		model.addAttribute("id", id);
		return "terminalservertable";
	}
	
	
	
	@RequestMapping(value = "schedule", method = RequestMethod.GET)
	public String restschedule(Model model) {
		model.addAttribute("top_menu_active", "menu_shedule");	
		model.addAttribute("slave_menu_show", "shedule");
		model.addAttribute("slave_menu_active", "schedule");
		return "scheduletable";
	}

	@RequestMapping(value = "mactable", method = RequestMethod.GET)
	public String restMacAdress(Model model) {
		return "restmactable";
	}


	@RequestMapping(value = "restlogging", method = RequestMethod.GET)
	public String restLogging(Model model) {
		model.addAttribute("top_menu_active", "menu_shedule");	
		model.addAttribute("slave_menu_show", "shedule");
		model.addAttribute("slave_menu_active", "restlogging");
		return "restlogging";
	}

	@RequestMapping(value = "systemparam", method = RequestMethod.GET)
	public String systemParam(Model model) throws SQLException {
		Map<String, String> globalParamMap = globalParamRepository.getMap();
		
		DTOSystemParam dtoSystemParam = new DTOSystemParam();
		String detectSystem = System.getProperty("os.name");
		dtoSystemParam.setDetectSystem(detectSystem);
		dtoSystemParam.setLinuxSavePath((String) globalParamMap.get("PathToBackUPLinux"));
		dtoSystemParam.setWinSavePath((String) globalParamMap.get("PathToBackUPWin"));
		model.addAttribute("dtoSystemParam", dtoSystemParam);
		System.out.println(dtoSystemParam);
		model.addAttribute("top_menu_active", "menu_aministration");	
		model.addAttribute("slave_menu_show", "aministration");
		model.addAttribute("slave_menu_active", "systemparam");
		return "systemparam";
	}

	@RequestMapping(value = "systemparam", method = RequestMethod.POST)
	public String systemParamUpdate(@ModelAttribute("dtoSystemParam") @Valid DTOSystemParam dtoSystemParam,
			BindingResult result, Model model) {
		String detectSystem = System.getProperty("os.name");
		dtoSystemParam.setDetectSystem(detectSystem);
		String resultCheck = "Check path to save for " + detectSystem + "<br>";
		detectSystem = detectSystem.toLowerCase();
		String stringPath = "";
		if (detectSystem.contains("linux")) {
			stringPath = dtoSystemParam.getLinuxSavePath();
			resultCheck = resultCheck + stringPath + "<br>";
		}

		if (detectSystem.contains("win")) {
			stringPath = dtoSystemParam.getWinSavePath();
			resultCheck = resultCheck + stringPath + "<br>";
		}

		String data = "temp";

		File file ;
		File directory = new File(stringPath);
		if (directory.exists()) {
			resultCheck = resultCheck + "Directory is exist" + "<br>";
			model.addAttribute("resultupdate", resultCheck);

		} else {
			resultCheck = resultCheck + "Directory is not exist" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
			if (directory.mkdirs()) {
				resultCheck = resultCheck + "Directory is created" + "<br>";
			} else {
				resultCheck = resultCheck + "Directory is not created" + "<br>";
				resultCheck = resultCheck + "Path is not save" + "<br>";
				model.addAttribute("resultupdate", resultCheck);
				return "systemparam";
			}
		}
		resultCheck = resultCheck + "Try to make Directory " +stringPath + "temp123" + "<br>";
		directory = new File(stringPath + "temp123");
		if (directory.exists()) {
			resultCheck = resultCheck + "Directory is exist" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
		}else{
			if (directory.mkdirs()) {
				resultCheck = resultCheck + "Directory is created" + "<br>";
			} else {
				resultCheck = resultCheck + "Directory is not created" + "<br>";
				resultCheck = resultCheck + "Path is not save" + "<br>";
				model.addAttribute("resultupdate", resultCheck);
				return "systemparam";
			}
		}
		
		resultCheck = resultCheck + "Try to make file " +stringPath + "temp123/temp123" + "<br>";
		file = new File(stringPath + "temp123/temp123");
		if (file.exists()) {
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "File is exist" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
		}else{
			try {
				if (file.createNewFile()) {
					resultCheck = resultCheck + stringPath + "temp123/temp123 " + "File is create" + "<br>";
				} else {
					resultCheck = resultCheck + stringPath + "temp123/temp123 " + "File not reate" + "<br>";
					model.addAttribute("resultupdate", resultCheck);
					return "systemparam";
				}
			} catch (IOException e) {
				resultCheck = resultCheck + stringPath + "temp123/temp123 " + "IOException create File" + "<br>";
				model.addAttribute("resultupdate", resultCheck);
				return "systemparam";
			}
		}
		
		FileWriter writer;
		try {
			writer = new FileWriter(stringPath + "temp123/temp123", false);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "IOException write File" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
			return "systemparam";
		}
		
		resultCheck = resultCheck + stringPath + "temp123/temp123 " + "write File" + "<br>";
		
		Reader reader;
		StringBuilder resultFileRead = new StringBuilder();
		try {
			reader = new FileReader(file);
			char[] buf = new char[1024];
			int r = 0;

	        while ((r = reader.read(buf)) != -1) {
	        	resultFileRead.append(buf, 0, r);
	        }
	        reader.close();
	        
		} catch (IOException e) {
			
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "IOException read File" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
			return "systemparam";
		}
		
		if (data.equals(resultFileRead.toString())){
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "read File" + "<br>";	
		}else{
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "read File with error" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
			return "systemparam";
		}
		
		
		if (file.delete()){
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "File deleted" + "<br>";
		}else{
			resultCheck = resultCheck + stringPath + "temp123/temp123 " + "File id not deleted" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
			return "systemparam";
		}
		
		if (directory.delete()){
			resultCheck = resultCheck + stringPath + "temp123 " + "Directory deleted" + "<br>";
		}else{
			resultCheck = resultCheck + stringPath + "temp123 " + "Directory id not deleted" + "<br>";
			model.addAttribute("resultupdate", resultCheck);
			return "systemparam";
		}
			
		
		Map<String, String> globalParamMap = new HashMap<String, String>();

		globalParamMap.put("PathToBackUPLinux", dtoSystemParam.getLinuxSavePath());
		globalParamMap.put("PathToBackUPWin", dtoSystemParam.getWinSavePath());

		globalParamRepository.setMap(globalParamMap);
		model.addAttribute("dtoSystemParam", dtoSystemParam);

		resultCheck = resultCheck + "SUCCESS Save";
		model.addAttribute("resultupdate", resultCheck);
		System.out.println(dtoSystemParam);
		model.addAttribute("top_menu_active", "menu_aministration");	
		model.addAttribute("slave_menu_show", "aministration");
		model.addAttribute("slave_menu_active", "systemparam");
		return "systemparam";
	}
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
