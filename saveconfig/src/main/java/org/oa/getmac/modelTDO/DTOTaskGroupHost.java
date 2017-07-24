/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOTaskGroupHost {
	@JsonProperty("DT_RowId")
	private int id;
	private char type;
	private int taskId;
	private int groupHostId;

	public DTOTaskGroupHost() {

	}

	public DTOTaskGroupHost(int id, char type, int taskId, int groupHostId) {
		super();
		this.id = id;
		this.type = type;
		this.taskId = taskId;
		this.groupHostId = groupHostId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getGroupHostId() {
		return groupHostId;
	}

	public void setGroupHostId(int groupHostId) {
		this.groupHostId = groupHostId;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DTOTaskGroupHost [getId()=" + getId() + ", getTaskId()=" + getTaskId() + ", getGroupHostId()="
				+ getGroupHostId() + ", getType()=" + getType() + "]";
	}
}
