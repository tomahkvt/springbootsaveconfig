/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;
import org.oa.getmac.model.DeviceGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOTask {
	@JsonProperty("DT_RowId")
	private int taskid;
	@NotEmpty
	private String name;

	private Set<DeviceGroup> groups = new HashSet<DeviceGroup>();
	private Set<DeviceGroup> excludeGroups = new HashSet<DeviceGroup>();
	private Set<DTODevice> devices = new HashSet<DTODevice>();

	public DTOTask() {
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

	public Set<DeviceGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<DeviceGroup> groups) {
		this.groups = groups;
	}

	public Set<DTODevice> getDevices() {
		return devices;
	}

	public void setDevices(Set<DTODevice> devices) {
		this.devices = devices;
	}

	public Set<DeviceGroup> getExcludeGroups() {
		return excludeGroups;
	}

	public void setExcludeGroups(Set<DeviceGroup> excludeGroups) {
		this.excludeGroups = excludeGroups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((devices == null) ? 0 : devices.hashCode());
		result = prime * result + ((excludeGroups == null) ? 0 : excludeGroups.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + taskid;
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
		DTOTask other = (DTOTask) obj;
		if (devices == null) {
			if (other.devices != null)
				return false;
		} else if (!devices.equals(other.devices))
			return false;
		if (excludeGroups == null) {
			if (other.excludeGroups != null)
				return false;
		} else if (!excludeGroups.equals(other.excludeGroups))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (taskid != other.taskid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DTOTask [getTaskid()=" + getTaskid() + ", getName()=" + getName() + ", getGroups()=" + getGroups()
				+ ", getDevices()=" + getDevices() + ", getExcludeGroups()=" + getExcludeGroups() + ", hashCode()="
				+ hashCode() + "]";
	}
}
