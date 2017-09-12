/*
 * Service Controller for More
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.More;
import org.oa.getmac.modelTDO.DTOCommand;
import org.oa.getmac.modelTDO.DTOMore;
import org.oa.getmac.repository.MoreRepository;
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
@RequestMapping("/api/more")
public class MoreService {
	private static Logger log = Logger.getLogger(MoreService.class);

	@Autowired
	private MoreRepository moreRepository;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{idTemplate}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DTOMore> getListMore(@PathVariable int idTemplate) {
		List<DTOMore> dtoMores = new ArrayList<>();
		List<More> mores = moreRepository.findById(idTemplate);
		System.out.println(mores);
		for(More more : mores){
			dtoMores.add(more.getDTOMore());
		}
		return dtoMores;
	}	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DTOCommand add(@RequestBody DTOMore dtoMore) {
		More more = new More();
		more.setDTOMore(dtoMore);
		moreRepository.create(more);
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody DTOCommand update(@RequestBody DTOMore dtoMore) {
		More more = new More();
		more.setDTOMore(dtoMore);
		System.out.println(more);
		moreRepository.update(more);
		return null;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String delete(@PathVariable int id) {
		More more = new More();
		more.setId(id);
		//System.out.println("delete" + command);
		moreRepository.delete(more);
		return "OK";
	}
	
}
