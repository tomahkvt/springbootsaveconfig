/*
 * Class for mapping table "device_type"
 */
package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "device_type")
@XmlRootElement(name = "DeviceType")
public class DeviceType {
	@Id
	@XmlElement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement
	@Column(name = "device_type", length = 50)
	private String deviceType;

	public DeviceType() {
	}

	public DeviceType(String deviceType) {
		super();

		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "DeviceType [id=" + id + ", deviceType=" + deviceType + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + id;
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
		DeviceType other = (DeviceType) obj;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
