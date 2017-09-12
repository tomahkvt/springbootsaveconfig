/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOSchedule {

	@JsonProperty("DT_RowId")
	private int scheduleId;
	private int taskid;
	private Integer dayOfWeek;
	private Integer hour;
	private Integer minute;

	public DTOSchedule() {

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
		return "DTOSchedule [getScheduleId()=" + getScheduleId() + ", getDayOfWeek()=" + getDayOfWeek() + ", getHour()="
				+ getHour() + ", getMinute()=" + getMinute() + ", getTaskid()=" + getTaskid() + "]";
	}

}
