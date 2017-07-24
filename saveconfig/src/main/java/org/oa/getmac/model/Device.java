/*
 * Class for mapping table "Device"
 */
package org.oa.getmac.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.oa.getmac.modelTDO.DTODevice;
import org.oa.getmac.modelTDO.DTOTemplate;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "device")
@XmlRootElement(name = "Device")
public class Device {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne()
	@JoinColumn(name = "id_template")
	@XmlElement(name = "template")
	@JsonProperty("template")
	private Template template;

	@Column(name = "device_ip", length = 50)
	private String deviceIp;

	@XmlElement
	@Column(name = "device_name", length = 50)
	private String deviceName;

	@XmlElement
	@Column(name = "promt1", length = 50)
	private String promt1;

	@XmlElement
	@Column(name = "promt2", length = 50)
	private String promt2;

	@XmlElement
	@Column(name = "promt3", length = 50)
	private String promt3;

	@XmlElement
	@Column(name = "promt4", length = 50)
	private String promt4;

	@XmlElement
	@Column(name = "username1", length = 50)
	private String username1;

	@XmlElement
	@Column(name = "password1", length = 50)
	private String password1;

	@XmlElement
	@Column(name = "username2", length = 50)
	private String username2;

	@XmlElement
	@Column(name = "password2", length = 50)
	private String password2;

	@XmlElement
	@Column(name = "username3", length = 50)
	private String username3;

	@XmlElement
	@Column(name = "password3", length = 50)
	private String password3;

	@XmlElement
	@Column(name = "username4", length = 50)
	private String username4;

	@XmlElement
	@Column(name = "password4", length = 50)
	private String password4;

	@XmlElement
	@Column(name = "switch_enable", length = 50)
	private boolean switchEnable;

	@XmlElement
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "device_groups", joinColumns = { @JoinColumn(name = "deviceid") }, inverseJoinColumns = {
			@JoinColumn(name = "groupid") })
	private Set<DeviceGroup> groups = new HashSet<DeviceGroup>();

	@Transient
	private Set<DeviceGroup> rightGroups = new HashSet<DeviceGroup>();

	public Device() {

	}

	public Device(int id, Template template, String deviceIp, String deviceName, String deviceUsername,
			String devicePassw, String promt1, String promt2, String promt3, String promt4, String username1,
			String password1, String username2, String password2, String username3, String password3, String username4,
			String password4, boolean switchEnable, Set<DeviceGroup> groups) {
		super();
		this.id = id;
		this.template = template;
		this.deviceIp = deviceIp;
		this.deviceName = deviceName;
		this.promt1 = promt1;
		this.promt2 = promt2;
		this.promt3 = promt3;
		this.promt4 = promt4;
		this.username1 = username1;
		this.password1 = password1;
		this.username2 = username2;
		this.password2 = password2;
		this.username3 = username3;
		this.password3 = password3;
		this.username4 = username4;
		this.password4 = password4;
		this.switchEnable = switchEnable;
		this.groups = groups;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPromt1() {
		return promt1;
	}

	public void setPromt1(String promt1) {
		this.promt1 = promt1;
	}

	public String getPromt2() {
		return promt2;
	}

	public void setPromt2(String promt2) {
		this.promt2 = promt2;
	}

	public String getPromt3() {
		return promt3;
	}

	public void setPromt3(String promt3) {
		this.promt3 = promt3;
	}

	public String getPromt4() {
		return promt4;
	}

	public void setPromt4(String promt4) {
		this.promt4 = promt4;
	}

	public String getUsername1() {
		return username1;
	}

	public void setUsername1(String username1) {
		this.username1 = username1;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getUsername3() {
		return username3;
	}

	public void setUsername3(String username3) {
		this.username3 = username3;
	}

	public String getPassword3() {
		return password3;
	}

	public void setPassword3(String password3) {
		this.password3 = password3;
	}

	public String getUsername4() {
		return username4;
	}

	public void setUsername4(String username4) {
		this.username4 = username4;
	}

	public String getPassword4() {
		return password4;
	}

	public void setPassword4(String password4) {
		this.password4 = password4;
	}

	public boolean isSwitchEnable() {
		return switchEnable;
	}

	public void setSwitchEnable(boolean switchEnable) {
		this.switchEnable = switchEnable;
	}

	public Set<DeviceGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<DeviceGroup> groups) {
		this.groups = groups;
	}

	public Set<DeviceGroup> getRightGroups() {
		return rightGroups;
	}

	public void setRightGroups(Set<DeviceGroup> rightGroups) {
		this.rightGroups = rightGroups;
	}

	public DTODevice getDeviceDTOforTable() {
		DTODevice dtoDevice = new DTODevice();
		DTOTemplate dtoTemplate = new DTOTemplate();
		dtoDevice.setId(this.getId());
		dtoDevice.setDeviceName(this.getDeviceName());
		dtoDevice.setDeviceIp(this.getDeviceIp());
		dtoTemplate.setId(this.getTemplate().getId());
		dtoTemplate.setTemplateName(this.getTemplate().getTemplateName());
		dtoDevice.setTemplate(dtoTemplate);
		dtoDevice.setSwitchEnable(this.isSwitchEnable());
		return dtoDevice;
	}

	public DTODevice getDeviceDTOforList() {
		DTODevice dtoDevice = new DTODevice();
		DTOTemplate dtoTemplate = new DTOTemplate();
		dtoDevice.setId(this.getId());
		dtoDevice.setDeviceName(this.getDeviceName());
		return dtoDevice;
	}

	public DTODevice getDTODevice() {
		DTODevice dtoDevice = new DTODevice();
		DTOTemplate dtoTemplate = new DTOTemplate();
		dtoTemplate.setId(this.getTemplate().getId());
		dtoTemplate.setTemplateName(this.getTemplate().getTemplateName());
		dtoDevice.setId(this.getId());
		dtoDevice.setTemplate(dtoTemplate);
		dtoDevice.setDeviceIp(this.getDeviceIp());
		dtoDevice.setDeviceName(this.getDeviceName());
		dtoDevice.setPromt1(this.getPromt1());
		dtoDevice.setPromt2(this.getPromt2());
		dtoDevice.setPromt3(this.getPromt3());
		dtoDevice.setPromt4(this.getPromt4());

		dtoDevice.setUsername1(this.getUsername1());

		dtoDevice.setUsername2(this.getUsername2());
		dtoDevice.setUsername3(this.getUsername3());
		dtoDevice.setUsername4(this.getUsername4());
		if ((this.getPassword1() == null) || this.getPassword1().isEmpty()) {
			dtoDevice.setPassword1("Is Empty");
		} else {
			dtoDevice.setPassword1("Is not Empty");
		}

		if ((this.getPassword2() == null) || this.getPassword2().isEmpty()) {
			dtoDevice.setPassword2("Is Empty");
		} else {
			dtoDevice.setPassword2("Is not Empty");
		}

		if ((this.getPassword3() == null) || this.getPassword3().isEmpty()) {
			dtoDevice.setPassword3("Is Empty");
		} else {
			dtoDevice.setPassword3("Is not Empty");
		}

		if ((this.getPassword4() == null) || this.getPassword4().isEmpty()) {
			dtoDevice.setPassword4("Is Empty");
		} else {
			dtoDevice.setPassword4("Is not Empty");
		}
		dtoDevice.setGroups(this.getGroups());
		dtoDevice.setRightGroups(this.getRightGroups());
		return dtoDevice;
	}

	public void setDTODevice(DTODevice dtoDevice) {
		Template template = new Template();
		template.setId(dtoDevice.getTemplate().getId());
		template.setTemplateName(dtoDevice.getTemplate().getTemplateName());
		this.setId(dtoDevice.getId());
		this.setTemplate(template);
		this.setDeviceIp(dtoDevice.getDeviceIp());
		this.setDeviceName(dtoDevice.getDeviceName());
		this.setPromt1(dtoDevice.getPromt1());
		this.setPromt2(dtoDevice.getPromt2());
		this.setPromt3(dtoDevice.getPromt3());
		this.setPromt4(dtoDevice.getPromt4());
		this.setUsername1(dtoDevice.getUsername1());
		this.setUsername2(dtoDevice.getUsername2());
		this.setUsername3(dtoDevice.getUsername3());
		this.setUsername4(dtoDevice.getUsername4());
		if ((dtoDevice.getPassword1() == null) || dtoDevice.getPassword1().isEmpty()) {
			this.setPassword1(null);
		} else {
			this.setPassword1(dtoDevice.getPassword1());
		}
		if ((dtoDevice.getPassword2() == null) || dtoDevice.getPassword2().isEmpty()) {
			this.setPassword2(null);
		} else {
			this.setPassword2(dtoDevice.getPassword2());
		}

		if ((dtoDevice.getPassword3() == null) || dtoDevice.getPassword3().isEmpty()) {
			this.setPassword3(null);
		} else {
			this.setPassword3(dtoDevice.getPassword3());
		}

		if ((dtoDevice.getPassword4() == null) || dtoDevice.getPassword4().isEmpty()) {
			this.setPassword4(null);
		} else {
			this.setPassword4(dtoDevice.getPassword4());
		}

		this.setSwitchEnable(dtoDevice.isSwitchEnable());
		this.setGroups(dtoDevice.getGroups());
		this.setRightGroups(dtoDevice.getRightGroups());

	}

	@Override
	public String toString() {
		return "Device [getId()=" + getId() + ", getTemplate()=" + getTemplate() + ", getDeviceIp()=" + getDeviceIp()
				+ ", getDeviceName()=" + getDeviceName() + ", getPromt1()=" + getPromt1() + ", getPromt2()="
				+ getPromt2() + ", getPromt3()=" + getPromt3() + ", getPromt4()=" + getPromt4() + ", getUsername1()="
				+ getUsername1() + ", getPassword1()=" + getPassword1() + ", getUsername2()=" + getUsername2()
				+ ", getPassword2()=" + getPassword2() + ", getUsername3()=" + getUsername3() + ", getPassword3()="
				+ getPassword3() + ", getUsername4()=" + getUsername4() + ", getPassword4()=" + getPassword4()
				+ ", isSwitchEnable()=" + isSwitchEnable() + ", getGroups()=" + getGroups() + ", getRightGroups()="
				+ getRightGroups() + ", getDeviceDTOforTable()=" + getDeviceDTOforTable() + ", getDTODevice()="
				+ getDTODevice() + ", hashCode()=" + hashCode() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceIp == null) ? 0 : deviceIp.hashCode());
		result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + id;
		result = prime * result + ((password1 == null) ? 0 : password1.hashCode());
		result = prime * result + ((password2 == null) ? 0 : password2.hashCode());
		result = prime * result + ((password3 == null) ? 0 : password3.hashCode());
		result = prime * result + ((password4 == null) ? 0 : password4.hashCode());
		result = prime * result + ((promt1 == null) ? 0 : promt1.hashCode());
		result = prime * result + ((promt2 == null) ? 0 : promt2.hashCode());
		result = prime * result + ((promt3 == null) ? 0 : promt3.hashCode());
		result = prime * result + ((promt4 == null) ? 0 : promt4.hashCode());
		result = prime * result + ((rightGroups == null) ? 0 : rightGroups.hashCode());
		result = prime * result + (switchEnable ? 1231 : 1237);
		result = prime * result + ((template == null) ? 0 : template.hashCode());
		result = prime * result + ((username1 == null) ? 0 : username1.hashCode());
		result = prime * result + ((username2 == null) ? 0 : username2.hashCode());
		result = prime * result + ((username3 == null) ? 0 : username3.hashCode());
		result = prime * result + ((username4 == null) ? 0 : username4.hashCode());
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
		Device other = (Device) obj;
		if (deviceIp == null) {
			if (other.deviceIp != null)
				return false;
		} else if (!deviceIp.equals(other.deviceIp))
			return false;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (id != other.id)
			return false;
		if (password1 == null) {
			if (other.password1 != null)
				return false;
		} else if (!password1.equals(other.password1))
			return false;
		if (password2 == null) {
			if (other.password2 != null)
				return false;
		} else if (!password2.equals(other.password2))
			return false;
		if (password3 == null) {
			if (other.password3 != null)
				return false;
		} else if (!password3.equals(other.password3))
			return false;
		if (password4 == null) {
			if (other.password4 != null)
				return false;
		} else if (!password4.equals(other.password4))
			return false;
		if (promt1 == null) {
			if (other.promt1 != null)
				return false;
		} else if (!promt1.equals(other.promt1))
			return false;
		if (promt2 == null) {
			if (other.promt2 != null)
				return false;
		} else if (!promt2.equals(other.promt2))
			return false;
		if (promt3 == null) {
			if (other.promt3 != null)
				return false;
		} else if (!promt3.equals(other.promt3))
			return false;
		if (promt4 == null) {
			if (other.promt4 != null)
				return false;
		} else if (!promt4.equals(other.promt4))
			return false;
		if (rightGroups == null) {
			if (other.rightGroups != null)
				return false;
		} else if (!rightGroups.equals(other.rightGroups))
			return false;
		if (switchEnable != other.switchEnable)
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		if (username1 == null) {
			if (other.username1 != null)
				return false;
		} else if (!username1.equals(other.username1))
			return false;
		if (username2 == null) {
			if (other.username2 != null)
				return false;
		} else if (!username2.equals(other.username2))
			return false;
		if (username3 == null) {
			if (other.username3 != null)
				return false;
		} else if (!username3.equals(other.username3))
			return false;
		if (username4 == null) {
			if (other.username4 != null)
				return false;
		} else if (!username4.equals(other.username4))
			return false;
		return true;
	}

}
