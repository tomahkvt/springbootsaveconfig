/*
 * class for mapping table "terminal_server"
 */
package org.oa.getmac.model;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;
import org.oa.getmac.modelTDO.DTOTerminalServer;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@DynamicUpdate
@Table(name = "terminal_server")
@XmlRootElement(name = "terminal_server")
public class TerminalServer {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "device_ip", length = 50)
	private String deviceIp;

	@XmlElement
	@Column(name = "device_name", length = 50)
	private String deviceName;

	@XmlElement
	@Column(name = "device_username1", length = 50)
	private String username1;

	@XmlElement
	@Column(name = "device_passw1", length = 50)
	private String passw1;

	@XmlElement
	@Column(name = "device_promt1", length = 50)
	private String promt1;

	@XmlElement
	@Column(name = "device_username2", length = 50)
	private String username2;

	@XmlElement
	@Column(name = "device_passw2", length = 50)
	private String passw2;

	@XmlElement
	@Column(name = "device_promt2", length = 50)
	private String promt2;

	public TerminalServer() {

	}

	public TerminalServer(String deviceIp, String deviceName, String username1, String passw1, String promt1,
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

	public DTOTerminalServer getDTOTerminalServerforList() {
		DTOTerminalServer dtoTerminalServer = new DTOTerminalServer();
		dtoTerminalServer.setId(this.getId());
		dtoTerminalServer.setDeviceName(this.getDeviceName());
		dtoTerminalServer.setDeviceIp(this.getDeviceIp());
		return dtoTerminalServer;
	}

	public DTOTerminalServer getDTOTerminalServer() {
		DTOTerminalServer dtoTerminalServer = new DTOTerminalServer();
		dtoTerminalServer.setId(this.getId());
		dtoTerminalServer.setDeviceName(this.getDeviceName());
		dtoTerminalServer.setDeviceIp(this.getDeviceIp());
		dtoTerminalServer.setUsername1(this.getUsername1());
		if ((this.getPassw1() == null) || this.getPassw1().isEmpty()) {
			dtoTerminalServer.setPassw1("Is not Empty");
		} else {
			dtoTerminalServer.setPassw1("Is Empty");
		}
		dtoTerminalServer.setPromt1(this.getPromt1());
		dtoTerminalServer.setUsername2(this.getUsername2());
		if ((this.getPassw2() == null) || this.getPassw2().isEmpty()) {
			dtoTerminalServer.setPassw2("Is not Empty");
		} else {
			dtoTerminalServer.setPassw2("Is Empty");
		}

		dtoTerminalServer.setPromt2(this.getPromt2());

		return dtoTerminalServer;
	}

	@Override
	public String toString() {
		return "TerminalServer [getId()=" + getId() + ", getDeviceIp()=" + getDeviceIp() + ", getDeviceName()="
				+ getDeviceName() + ", getUsername1()=" + getUsername1() + ", getPassw1()=" + getPassw1()
				+ ", getPromt1()=" + getPromt1() + ", getUsername2()=" + getUsername2() + ", getPassw2()=" + getPassw2()
				+ ", getPromt2()=" + getPromt2() + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceIp == null) ? 0 : deviceIp.hashCode());
		result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result + id;
		result = prime * result + ((passw1 == null) ? 0 : passw1.hashCode());
		result = prime * result + ((passw2 == null) ? 0 : passw2.hashCode());
		result = prime * result + ((promt1 == null) ? 0 : promt1.hashCode());
		result = prime * result + ((promt2 == null) ? 0 : promt2.hashCode());
		result = prime * result + ((username1 == null) ? 0 : username1.hashCode());
		result = prime * result + ((username2 == null) ? 0 : username2.hashCode());
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
		TerminalServer other = (TerminalServer) obj;
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
		if (id != other.id)
			return false;
		if (passw1 == null) {
			if (other.passw1 != null)
				return false;
		} else if (!passw1.equals(other.passw1))
			return false;
		if (passw2 == null) {
			if (other.passw2 != null)
				return false;
		} else if (!passw2.equals(other.passw2))
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
		return true;
	}

	public void setDTOTerminalServer(DTOTerminalServer dtoTerminalServer) {

		this.setId(dtoTerminalServer.getId());
		this.setDeviceName(dtoTerminalServer.getDeviceName());
		this.setDeviceIp(dtoTerminalServer.getDeviceIp());
		this.setUsername1(dtoTerminalServer.getUsername1());
		if ((dtoTerminalServer.getPassw1() == null) || dtoTerminalServer.getPassw1().isEmpty()) {
			this.setPassw1(null);
		} else {
			this.setPassw1(dtoTerminalServer.getPassw1());
		}
		this.setPromt1(dtoTerminalServer.getPromt1());
		this.setUsername2(dtoTerminalServer.getUsername2());
		if ((dtoTerminalServer.getPassw2() == null) || dtoTerminalServer.getPassw2().isEmpty()) {
			this.setPassw2(null);
		} else {
			this.setPassw2(dtoTerminalServer.getPassw2());
		}
		this.setPromt2(dtoTerminalServer.getPromt2());

	}

	public Boolean isReachableIcmp() {
		boolean reachable;
		try {
			InetAddress address = InetAddress.getByName(this.deviceIp);
			reachable = address.isReachable(2000);

		} catch (Exception e) {
			reachable = false;
		}
		return reachable;
	}

	public Boolean isReachableTcp() {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(this.deviceIp, 22), 2000);
			socket.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
