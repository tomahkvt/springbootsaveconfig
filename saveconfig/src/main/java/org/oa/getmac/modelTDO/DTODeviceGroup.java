/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTODeviceGroup {
	@JsonProperty("DT_RowId")
	private int groupid;
	private String name;

	public DTODeviceGroup() {

	}

	public DTODeviceGroup(String name) {
		super();
		this.name = name;

	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [getGroupid()=" + getGroupid() + ", getName()=" + getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupid;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DTODeviceGroup other = (DTODeviceGroup) obj;
		if (groupid != other.groupid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
