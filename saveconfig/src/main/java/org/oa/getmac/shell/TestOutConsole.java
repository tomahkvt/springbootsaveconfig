package org.oa.getmac.shell;

import org.oa.getmac.model.TaskComplited;
import org.oa.getmac.repository.TaskComplitedLoggingRepository;

public class TestOutConsole implements OutConsole {

	private StringBuilder result = new StringBuilder();
	
	public TestOutConsole() {
	
	}
	
	@Override
	public void sendMessage(String string) {
		result.append(string);
	}
	
	@Override
	public void sendMessage(StringBuilder stringBuilder) {
		result.append(stringBuilder);
	}
	
	public StringBuilder getResult(){
		return result;
	}

	@Override
	public void writeLog(String comment, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatusTaskComplited() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatusTaskComplited(String statusTaskComplited) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatusTaskComplitedLogging() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatusTaskComplitedLogging(String statusTaskComplitedLogging) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTaskComplited(TaskComplited taskComplited) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTaskComplitedLoggingRepository(TaskComplitedLoggingRepository taskComplitedLoggingRepository) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
