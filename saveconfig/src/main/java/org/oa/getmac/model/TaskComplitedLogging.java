/*
 * class for mapping table "tasks_complited_loging"
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
@Table(name = "tasks_complited_loging")
@XmlRootElement(name = "tasks_complited_loging")
public class TaskComplitedLogging {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtaskcomplog")
	private int idtaskcomplog;

	@XmlElement(name = "taskComplited")
	@JsonProperty("taskcompid")
	private int taskcompid;

	@XmlElement
	@Column(name = "comment", length = 100)
	private String comment;

	@XmlElement
	@Column(name = "status", length = 100)
	private String status;

	@XmlElement(name = "timeinsert")
	@Column(name = "timeinsert", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = CustomDateSerializer.class)
	Date timeinsert;

	public TaskComplitedLogging() {

	}

	public int getIdtaskcomplog() {
		return idtaskcomplog;
	}

	public void setIdtaskcomplog(int idtaskcomplog) {
		this.idtaskcomplog = idtaskcomplog;
	}

	public int getTaskcompid() {
		return taskcompid;
	}

	public void setTaskcompid(int taskcompid) {
		this.taskcompid = taskcompid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimeinsert() {
		return timeinsert;
	}

	public void setTimeinsert(Date timeinsert) {
		this.timeinsert = timeinsert;
	}

	@Override
	public String toString() {
		return "TaskComplitedLogging [getIdtaskcomplog()=" + getIdtaskcomplog() + ", getTaskcompid()=" + getTaskcompid()
				+ ", getComment()=" + getComment() + ", getStatus()=" + getStatus() + ", getTimeinsert()="
				+ getTimeinsert() + "]";
	}

}
