/*
 * Service controller for userrole
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Role;
import org.oa.getmac.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/userrole")
public class UserRoleService {
	private static Logger log = Logger.getLogger(UserRoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Role> getAllDTO() {
		List<Role> roles = roleRepository.findAll();
		return roles;
	}
}
