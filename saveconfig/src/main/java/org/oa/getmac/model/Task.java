/*
 * class for mapping table"tasks"
 */
package org.oa.getmac.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.oa.getmac.modelTDO.DTODevice;
import org.oa.getmac.modelTDO.DTOTask;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tasks")
@XmlRootElement(name = "tasks")
public class Task {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskid")
	private int taskid;

	@XmlElement
	@Column(name = "taskname", length = 50)
	private String name;

	@XmlElement
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tasks_group", joinColumns = { @JoinColumn(name = "taskid") }, inverseJoinColumns = {
			@JoinColumn(name = "group_id") })
	private Set<DeviceGroup> groups = new HashSet<DeviceGroup>();

	@XmlElement
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tasks_device", joinColumns = { @JoinColumn(name = "taskid") }, inverseJoinColumns = {
			@JoinColumn(name = "device_id") })
	private Set<Device> devices = new HashSet<Device>();

	@Transient
	private Set<DeviceGroup> excludeGroups = new HashSet<DeviceGroup>();

	public Task() {

	}

	public Task(String name) {
		super();
		this.name = name;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDTOTask(DTOTask dtoTask) {
		this.setTaskid(dtoTask.getTaskid());
		this.setName(dtoTask.getName());
		this.setGroups(dtoTask.getGroups());
		Set<Device> devices = new HashSet<>();
		for (DTODevice dtoDevice : dtoTask.getDevices()) {
			Device device = new Device();
			device.setId(dtoDevice.getId());
			devices.add(device);
		}
		this.setDevices(devices);
	}

	public DTOTask getDTOTaskForTable() {
		DTOTask dtoTask = new DTOTask();
		dtoTask.setTaskid(this.getTaskid());
		dtoTask.setName(this.getName());
		return dtoTask;
	}

	public Set<DeviceGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<DeviceGroup> groups) {
		this.groups = groups;
	}

	public Set<DeviceGroup> getExcludeGroups() {
		return excludeGroups;
	}

	public void setExcludeGroups(Set<DeviceGroup> excludeGroups) {
		this.excludeGroups = excludeGroups;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public DTOTask getDTOTask() {
		DTOTask dtoTask = new DTOTask();
		dtoTask.setTaskid(this.getTaskid());
		dtoTask.setName(this.getName());
		dtoTask.setGroups(this.getGroups());
		dtoTask.setExcludeGroups(this.getExcludeGroups());
		Set<DTODevice> dtoDevices = new HashSet<>();
		for (Device device : this.getDevices()) {
			dtoDevices.add(device.getDeviceDTOforList());
		}
		dtoTask.setDevices(dtoDevices);
		return dtoTask;

	}

}
