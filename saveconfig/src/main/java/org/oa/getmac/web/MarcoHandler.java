/*
 * Class for active console
 * Catch event
 */
package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.process.ControlGetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MarcoHandler extends AbstractWebSocketHandler {
	private static Logger log = Logger.getLogger(MarcoHandler.class);

	@Autowired
	ControlGetData controlGetData;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		controlGetData.addWebSocketSession(session);
		log.info(session);
		log.info("Connected");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("Received message: " + message.getPayload());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		controlGetData.delWebSocketSession(session);
	}
}
