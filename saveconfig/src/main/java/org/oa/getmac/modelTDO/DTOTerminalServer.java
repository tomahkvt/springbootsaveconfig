/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOTerminalServer {
	 private static final String IPADDRESS_PATTERN = 
				"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	@JsonProperty("DT_RowId")
	private int id;
	@NotEmpty
	@Pattern(regexp=IPADDRESS_PATTERN, message = "incorect formad")
	private String deviceIp;
	@NotEmpty
	private String deviceName;
	@NotEmpty
	private String username1;
	
	private String passw1;
	@NotEmpty
	private String promt1;

	private String username2;

	private String passw2;

	private String promt2;
	
	public DTOTerminalServer() {

	}

	public DTOTerminalServer(String deviceIp, String deviceName, String username1, String passw1, String promt1,
			String username2, String passw2, String promt2) {
		super();
		this.deviceIp = deviceIp;
		this.deviceName = deviceName;
		this.username1 = username1;
		this.passw1 = passw1;
		this.promt1 = promt1;
		this.username2 = username2;
		this.passw2 = passw2;
		this.promt2 = promt2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername1() {
		return username1;
	}

	public void setUsername1(String username1) {
		this.username1 = username1;
	}

	public String getPassw1() {
		return passw1;
	}

	public void setPassw1(String passw1) {
		this.passw1 = passw1;
	}

	public String getPromt1() {
		return promt1;
	}

	public void setPromt1(String promt1) {
		this.promt1 = promt1;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public String getPassw2() {
		return passw2;
	}

	public void setPassw2(String passw2) {
		this.passw2 = passw2;
	}

	public String getPromt2() {
		return promt2;
	}

	public void setPromt2(String promt2) {
		this.promt2 = promt2;
	}

	@Override
	public String toString() {
		return "DTOTerminalServer [getId()=" + getId() + ", getDeviceIp()=" + getDeviceIp() + ", getDeviceName()="
				+ getDeviceName() + ", getUsername1()=" + getUsername1() + ", getPassw1()=" + getPassw1()
				+ ", getPromt1()=" + getPromt1() + ", getUsername2()=" + getUsername2() + ", getPassw2()=" + getPassw2()
				+ ", getPromt2()=" + getPromt2() + "]";
	}
}
