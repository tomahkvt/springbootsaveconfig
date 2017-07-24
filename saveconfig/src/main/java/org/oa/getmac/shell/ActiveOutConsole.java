/*
 * Class for out log to Webconsole
 */
package org.oa.getmac.shell;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.oa.getmac.model.TaskComplited;
import org.oa.getmac.model.TaskComplitedLogging;
import org.oa.getmac.repository.TaskComplitedLoggingRepository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ActiveOutConsole implements OutConsole {
	
	private List<WebSocketSession> webSocketSessions;
	
	private TaskComplited taskComplited;
	
	private TaskComplitedLoggingRepository taskComplitedLoggingRepository;
	
	private String statusTaskComplited;
	
	private String statusTaskComplitedLogging;

	public ActiveOutConsole() {
	
	}
	
	@Override
	public void sendMessage(String string){
		for (WebSocketSession socketSession : this.webSocketSessions) {
			try {
				if ((socketSession != null) || socketSession.isOpen()){
					socketSession.sendMessage(new TextMessage(string));
				}
			} catch (IOException e) {
				//this.webSocketSessions.remove(socketSession);
				System.out.println("error socketSession " + socketSession);
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void sendMessage(StringBuilder stringBuilder) {
		this.sendMessage(stringBuilder.toString());
	}

	public List<WebSocketSession> getWebSocketSession() {
		return webSocketSessions;
	}

	public void setWebSocketSessions(List<WebSocketSession> list) {
		this.webSocketSessions = list;
	}

	@Override
	public void writeLog(String comment, String status) {
		TaskComplitedLogging taskComplitedLogging = new TaskComplitedLogging();
		taskComplitedLogging.setTaskcompid(taskComplited.getTaskcompid());
		taskComplitedLogging.setComment(comment);
		taskComplitedLogging.setStatus(status);
		taskComplitedLogging.setTimeinsert(new Date());
		taskComplitedLoggingRepository.create(taskComplitedLogging);
		
	}

	public void setTaskComplited(TaskComplited taskComplited) {
		this.taskComplited = taskComplited;
	}

	public void setTaskComplitedLoggingRepository(TaskComplitedLoggingRepository taskComplitedLoggingRepository) {
		this.taskComplitedLoggingRepository = taskComplitedLoggingRepository;
	}

	public String getStatusTaskComplited() {
		return statusTaskComplited;
	}

	public void setStatusTaskComplited(String statusTaskComplited) {
		this.statusTaskComplited = statusTaskComplited;
	}

	public String getStatusTaskComplitedLogging() {
		return statusTaskComplitedLogging;
	}

	public void setStatusTaskComplitedLogging(String statusTaskComplitedLogging) {
		this.statusTaskComplitedLogging = statusTaskComplitedLogging;
	}

	@Override
	public Object getResult() {
		return null;
	}

}
