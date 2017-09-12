/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

public class DTOCommand {
	private int id;
	private int idTemplate;
	private int order;
	private String command;
	private String waitFor;
	private Integer waitTime;
	private boolean save;

	public DTOCommand() {

	}

	public DTOCommand(int idTemplate, int order, String command, String waitFor, Integer waitTime, boolean save) {
		super();
		this.idTemplate = idTemplate;
		this.order = order;
		this.command = command;
		this.waitFor = waitFor;
		this.waitTime = waitTime;
		this.save = save;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(int idTemplate) {
		this.idTemplate = idTemplate;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getWaitFor() {
		return waitFor;
	}

	public void setWaitFor(String waitFor) {
		this.waitFor = waitFor;
	}

	public Integer getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Integer waitTime) {
		this.waitTime = waitTime;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	@Override
	public String toString() {
		return "Command [getId()=" + getId() + "\n" + ", getIdTemplate()=" + getIdTemplate() + "\n" + ", getOrder()="
				+ getOrder() + "\n" + ", getCommand()=" + getCommand() + "\n" + ", getWaitFor()=" + getWaitFor() + "\n"
				+ ", getWaitTime()=" + getWaitTime() + ", isSave()=" + isSave() + "]";
	}

}
