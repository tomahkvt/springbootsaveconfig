/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import java.util.HashSet;
import java.util.Set;
import org.oa.getmac.model.DeviceGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DTODevice {
	@JsonProperty("DT_RowId")
	private int id;
	private DTOTemplate template;
	private String deviceIp;
	private String deviceName;
	private String promt1;
	private String promt2;
	private String promt3;
	private String promt4;
	private String username1;
	private String password1;
	private String username2;
	private String password2;
	private String username3;
	private String password3;
	private String username4;
	private String password4;
	private boolean switchEnable;
	private Set<DeviceGroup> groups = new HashSet<DeviceGroup>();
	private Set<DeviceGroup> rightGroups = new HashSet<DeviceGroup>();

	public DTODevice() {
	}

	public DTODevice(int id, DTOTemplate template, String deviceIp, String deviceName, String deviceUsername,
			String devicePassw, String promt1, String promt2, String promt3, String promt4, String username1,
			String password1, String username2, String password2, String username3, String password3, String username4,
			String password4, boolean switchEnable, Set<DeviceGroup> groups, Set<DeviceGroup> rightGroups) {
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
		this.rightGroups = rightGroups;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DTOTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DTOTemplate template) {
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

	@Override
	public String toString() {
		return "DTODevice [getId()=" + getId() + ", getTemplate()=" + getTemplate() + ", getDeviceIp()=" + getDeviceIp()
				+ ", getDeviceName()=" + getDeviceName() + ", getPromt1()=" + getPromt1() + ", getPromt2()="
				+ getPromt2() + ", getPromt3()=" + getPromt3() + ", getPromt4()=" + getPromt4() + ", getUsername1()="
				+ getUsername1() + ", getPassword1()=" + getPassword1() + ", getUsername2()=" + getUsername2()
				+ ", getPassword2()=" + getPassword2() + ", getUsername3()=" + getUsername3() + ", getPassword3()="
				+ getPassword3() + ", getUsername4()=" + getUsername4() + ", getPassword4()=" + getPassword4()
				+ ", isSwitchEnable()=" + isSwitchEnable() + ", getGroups()=" + getGroups() + ", getRightGroups()="
				+ getRightGroups() + "]";
	}
}
