/*
 * File uses for mapping table "schedule"
 */

package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.oa.getmac.modelTDO.DTOSchedule;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "schedule")
@XmlRootElement(name = "schedule")
public class Schedule {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scheduleid")
	private int scheduleId;

	@XmlElement(name = "taskid")
	@JsonProperty("taskid")
	private int taskid;

	@XmlElement
	@Column(name = "day_of_week")
	private Integer dayOfWeek;

	@XmlElement
	@Column(name = "hour")
	private Integer hour;

	@XmlElement
	@Column(name = "minute")
	private Integer minute;

	public Schedule() {

	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	@Override
	public String toString() {
		return "Schedule [getScheduleId()=" + getScheduleId() + ", getDayOfWeek()=" + getDayOfWeek() + ", getHour()="
				+ getHour() + ", getMinute()=" + getMinute() + ", getTaskid()=" + getTaskid() + "]";
	}

	public DTOSchedule getDTOSchedule() {
		DTOSchedule dtoSchedule = new DTOSchedule();
		dtoSchedule.setScheduleId(this.getScheduleId());
		dtoSchedule.setTaskid(this.getTaskid());
		dtoSchedule.setDayOfWeek(this.getDayOfWeek());
		dtoSchedule.setHour(this.getHour());
		dtoSchedule.setMinute(this.getMinute());
		return dtoSchedule;
	}

	public void setDTOSchedule(DTOSchedule dtoSchedule) {
		this.setScheduleId(dtoSchedule.getScheduleId());
		this.setTaskid(dtoSchedule.getTaskid());
		this.setDayOfWeek(dtoSchedule.getDayOfWeek());
		this.setHour(dtoSchedule.getHour());
		this.setMinute(dtoSchedule.getMinute());
	}
}
