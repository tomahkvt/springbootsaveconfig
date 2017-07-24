/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import java.util.Date;

import javax.persistence.*;
import org.oa.getmac.config.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DTOTaskComplitedJoinTask implements DTResult {

	private int taskcompid;
	private String taskname;
	private String type;
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date stopTime;

	public DTOTaskComplitedJoinTask() {

	}

	public DTOTaskComplitedJoinTask(int taskcompid, String taskname, String type, String status, Date startTime,
			Date stopTime) {
		super();
		this.taskcompid = taskcompid;
		this.taskname = taskname;
		this.type = type;
		this.status = status;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	public int getTaskcompid() {
		return taskcompid;
	}

	public void setTaskcompid(int taskcompid) {
		this.taskcompid = taskcompid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	@Override
	public String toString() {
		return "DTOTaskComplitedJoinTask [getTaskcompid()=" + getTaskcompid() + ", getTaskname()=" + getTaskname()
				+ ", getType()=" + getType() + ", getStatus()=" + getStatus() + ", getStartTime()=" + getStartTime()
				+ ", getStopTime()=" + getStopTime() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((stopTime == null) ? 0 : stopTime.hashCode());
		result = prime * result + taskcompid;
		result = prime * result + ((taskname == null) ? 0 : taskname.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOTaskComplitedJoinTask other = (DTOTaskComplitedJoinTask) obj;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (stopTime == null) {
			if (other.stopTime != null)
				return false;
		} else if (!stopTime.equals(other.stopTime))
			return false;
		if (taskcompid != other.taskcompid)
			return false;
		if (taskname == null) {
			if (other.taskname != null)
				return false;
		} else if (!taskname.equals(other.taskname))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
