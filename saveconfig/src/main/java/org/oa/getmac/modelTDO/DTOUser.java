/*
 * class for display data in JSON
 */
package org.oa.getmac.modelTDO;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.oa.getmac.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "user")
public class DTOUser {
	@JsonProperty("DT_RowId")
	private String id;
	@NotEmpty
	private String username;
	private String password;
	private Boolean enable;
	private Set<Role> roles = new HashSet<Role>();
	private Set<Role> rightRoles = new HashSet<Role>();

	public DTOUser() {

	}

	public DTOUser(String username, String password, Boolean enable) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRightRoles() {
		return rightRoles;
	}

	public void setRightRoles(Set<Role> rightRoles) {
		this.rightRoles = rightRoles;
	}

	@Override
	public String toString() {
		return "DTOUser [getEnable()=" + getEnable() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getId()=" + getId() + ", getRoles()=" + getRoles() + ", getRightRoles()="
				+ getRightRoles() + "]";
	}
}
