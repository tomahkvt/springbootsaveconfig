/*
 * class for mapping table "tasks_complited"
 * this table uses for store data about task
 */
package org.oa.getmac.model;

import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.oa.getmac.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tasks_complited")
@XmlRootElement(name = "tasks_complited")
public class TaskComplited {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskcompid")
	private int taskcompid;

	@Column(name = "taskid")
	private int taskid;

	@XmlElement
	@Column(name = "type", length = 50)
	private String type;

	@XmlElement
	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "starttime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date startTime;

	@Column(name = "stoptime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date stopTime;

	public TaskComplited() {

	}

	public int getTaskcompid() {
		return taskcompid;
	}

	public void setTaskcompid(int taskcompid) {
		this.taskcompid = taskcompid;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
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

	public void setStopTime(Date stoptime) {
		this.stopTime = stoptime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((stopTime == null) ? 0 : stopTime.hashCode());
		result = prime * result + taskcompid;
		result = prime * result + taskid;
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
		TaskComplited other = (TaskComplited) obj;
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
		if (taskid != other.taskid)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskComplited [getTaskcompid()=" + getTaskcompid() + ", getTaskid()=" + getTaskid() + ", getType()="
				+ getType() + ", getStatus()=" + getStatus() + ", getStartTime()=" + getStartTime() + ", getStoptime()="
				+ getStopTime() + "]";
	}

}
