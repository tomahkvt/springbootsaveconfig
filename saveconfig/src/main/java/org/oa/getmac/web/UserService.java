/*
 * Service controller for Entity User
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.JsonResponse;
import org.oa.getmac.model.User;
import org.oa.getmac.modelTDO.DTOUser;
import org.oa.getmac.repository.UserRepository;
import org.oa.getmac.repository.UserRoleRepository;
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
@RequestMapping("/api/user")
public class UserService {
	private static Logger log = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/tableDTO", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOUser> getAllDTO() {
		List<DTOUser> dtoUsers = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user: users){
			dtoUsers.add(user.getDTOUserForTable());
		}
		return dtoUsers;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "getDTO/{username}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DTOUser getDTOUser(@PathVariable String username) {
		
		User user = userRepository.findByLogin(username);
		return user.getDTOUser();
	}	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse add(@RequestBody @Valid DTOUser dtoUser, BindingResult result) {
		JsonResponse jsonResponse = new JsonResponse();

		User user = new User();
		user.setDTOUser(dtoUser);
		System.out.println(dtoUser);
		System.out.println(user);
		if (!result.hasErrors()){
			if(userRepository.isExistByLogin(user)){
			result.rejectValue("username", "is exists","is exists");
			}
			if(user.getPassword() == null){
			result.rejectValue("password", "is null","is null");
			}

		}
		
		 if(!result.hasErrors()){
			 
			 jsonResponse.setStatus("SUCCESS");
			 userRepository.create(user);
			 String username = user.getUsername();
			 user = userRepository.findByLogin(username);
			 jsonResponse.setResult(user.getDTOUserForTable());
		 }else{
			 jsonResponse.setStatus("FAIL");
			 jsonResponse.setResult(result.getAllErrors());
			                 }
		return jsonResponse;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse update(@RequestBody @Valid DTOUser dtoUser, BindingResult result) {
		
		JsonResponse jsonResponse = new JsonResponse();
		
		User user = new User();
		System.out.println(dtoUser);
		user.setDTOUser(dtoUser);
		System.out.println(user);

		 if(!result.hasErrors()){
			 jsonResponse.setStatus("SUCCESS");
			 userRepository.update(user);
			 user = userRepository.findByLogin(dtoUser.getUsername());
			 jsonResponse.setResult(user.getDTOUserForTable());
		 }else{
			 jsonResponse.setStatus("FAIL");
			 jsonResponse.setResult(result.getAllErrors());
			 
			                 }
		return jsonResponse;
		
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody JsonResponse delete(@PathVariable String username) {
		JsonResponse jsonResponse = new JsonResponse();
		String result ;
		if (username.equals("admin")){
			result = "Username is admin can't delete";
			jsonResponse.setStatus("FAIL");
			jsonResponse.setResult(result);
			return jsonResponse;
		}
		User user = userRepository.findByLogin(username);
		if (user == null){
				result = "Username is not exists";
				jsonResponse.setStatus("FAIL");
				jsonResponse.setResult(result);
			}else{
				userRoleRepository.deleteForUser(user.getUsername());
				userRepository.delete(user);
				jsonResponse.setStatus("SUCCESS");
				result = "User with username:" + user.getUsername() + " is deleted";
				jsonResponse.setResult(result);
			
			}
		return jsonResponse;
	}
	
}
