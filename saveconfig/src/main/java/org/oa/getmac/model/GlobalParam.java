/*
 * Class for load global parametrs from DB
 */


package org.oa.getmac.model;

import java.sql.SQLException;
import java.util.Map;
import org.oa.getmac.repository.GlobalParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalParam {
	
	private GlobalParamRepository globalParamRepository;
	
	private Map<String, String> mapGlobalParam;
	private String OS;
	@Autowired
	public GlobalParam(GlobalParamRepository globalParamRepository) throws SQLException {
		this.setGlobalParamRepository(globalParamRepository);
		OS = System.getProperty("os.name").toLowerCase();
		mapGlobalParam = globalParamRepository.getMap();
	}
	
	private boolean isWindows() {
        return (this.OS.indexOf("win") >= 0);
    }
	
	private boolean isLinux() {
	    return (this.OS.indexOf("linux") >= 0);
	}
	
	
	public String getSavePath(){
		
		if (this.isWindows())return(mapGlobalParam.get("PathToBackUPWin"));
		if (this.isLinux())return(mapGlobalParam.get("PathToBackUPLinux"));
		return mapGlobalParam.get("PathToBackUPLinux");
	}

	public GlobalParamRepository getGlobalParamRepository() {
		return globalParamRepository;
	}

	public void setGlobalParamRepository(GlobalParamRepository globalParamRepository) {
		this.globalParamRepository = globalParamRepository;
	}
}
