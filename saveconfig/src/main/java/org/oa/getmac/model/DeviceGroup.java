/*
 * Class for mapping table "groups"
 */
package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.oa.getmac.modelTDO.DTODeviceGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "groups")
@XmlRootElement(name = "groups")
public class DeviceGroup {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groupid")
	private int groupid;

	@XmlElement
	@Column(name = "name", length = 50)
	private String name;

	public DeviceGroup() {

	}

	public DeviceGroup(String name) {
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

	public DTODeviceGroup getDTODeviceGroup() {
		DTODeviceGroup dtoGroup = new DTODeviceGroup();
		dtoGroup.setGroupid(this.getGroupid());
		dtoGroup.setName(this.getName());
		return dtoGroup;
	}

	public void setDTODeviceGroup(DTODeviceGroup dtoGroup) {
		this.setGroupid(dtoGroup.getGroupid());
		this.setName(dtoGroup.getName());
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
		DeviceGroup other = (DeviceGroup) obj;
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
