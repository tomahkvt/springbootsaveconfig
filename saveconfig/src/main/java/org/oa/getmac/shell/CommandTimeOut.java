package org.oa.getmac.shell;

public class CommandTimeOut extends Exception {
	public CommandTimeOut() {
		super();
	}

	public CommandTimeOut(String message) {
		super(message);
	}

	public CommandTimeOut(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandTimeOut(Throwable cause) {
		super(cause);
	}
}
