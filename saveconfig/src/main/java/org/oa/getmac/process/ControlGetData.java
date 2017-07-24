/*
 * This class for control Tread by WEb interface
 */
package org.oa.getmac.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ControlGetData {
	private static Logger log = Logger.getLogger(ControlGetData.class);
	private List<WebSocketSession> webSocketSessions = new ArrayList<>();
	private String status = "IDLE";

	private ThreadGetDataFromDevice threadGetDataFromDevice;

	@Autowired
	private ApplicationContext _aApplicationContext;

	public List<WebSocketSession> getWebSocketSessions() {
		return webSocketSessions;
	}

	public void setWebSocketSessions(List<WebSocketSession> webSocketSessions) {
		this.webSocketSessions = webSocketSessions;
	}

	public void send(String s) {
		for (WebSocketSession socketSession : this.webSocketSessions) {
			try {
				socketSession.sendMessage(new TextMessage(s));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addWebSocketSession(WebSocketSession webSocketSession) {
		webSocketSessions.add(webSocketSession);
	}

	public void delWebSocketSession(WebSocketSession webSocketSession) {
		webSocketSessions.remove(webSocketSession);
	}

	public void startmanual(int idTask) {

		if ((threadGetDataFromDevice == null) || (threadGetDataFromDevice.getState() == Thread.State.TERMINATED)) {
			threadGetDataFromDevice = (ThreadGetDataFromDevice) _aApplicationContext
					.getBean(ThreadGetDataFromDevice.class);
			threadGetDataFromDevice.setWebSocketSessions(this.getWebSocketSessions());
		}
		System.out.println("threadGetDataFromDevice = " + threadGetDataFromDevice.getState());
		threadGetDataFromDevice.setmanual(idTask);
		threadGetDataFromDevice.start();
		this.status = "STARTED";
	}

	public void stop() {
		threadGetDataFromDevice.doStop();
		this.status = "IDLE";
		log.info("stop");
	}

	public void pause() {
		threadGetDataFromDevice.doPause();
		this.status = "PAUSED";
		log.info("pause");
	}

	public void resume() {
		System.out.println(threadGetDataFromDevice.getState());
		threadGetDataFromDevice.doResume();
		log.info("resume");
		this.status = "STARTED";

	}

	public String getStatus() {
		if (threadGetDataFromDevice != null) {
			if (threadGetDataFromDevice.getState() == Thread.State.TERMINATED) {
				this.status = "IDLE";
			}
		}
		return this.status;
	}

}
