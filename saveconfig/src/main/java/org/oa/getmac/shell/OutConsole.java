package org.oa.getmac.shell;

import org.oa.getmac.model.TaskComplited;
import org.oa.getmac.repository.TaskComplitedLoggingRepository;

public interface OutConsole {

	public void sendMessage(String string);

	public void sendMessage(StringBuilder stringBuilder);
	
	public void writeLog(String comment, String status);
	
	public String getStatusTaskComplited();
	
	public void setStatusTaskComplited(String statusTaskComplited);
	
	public String getStatusTaskComplitedLogging();
	
	public void setStatusTaskComplitedLogging(String statusTaskComplitedLogging);

	public void setTaskComplited(TaskComplited taskComplited);

	public void setTaskComplitedLoggingRepository(TaskComplitedLoggingRepository taskComplitedLoggingRepository);

	public Object getResult();
	
	
	
	
}
