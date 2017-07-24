/*
 * Class uses for mapping table "role"
 * user's roles
 */

package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.oa.getmac.modelTDO.DTODevice;
import org.oa.getmac.modelTDO.DTOTemplate;
import org.oa.getmac.modelTDO.DTOTerminalServer;
import org.oa.getmac.modelTDO.DTOUser;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "role")
public class Role {
	
	
	@Column(name = "id_role")
	private Integer id;
	@Id
	@Column(name = "role", unique = true,length = 45)
	private String role;	
	
	@Column(name = "role_name" ,unique = true, length = 45)
	private String role_name;

	public Role() {
		super();
	}
	
	public Role(Integer id, String role, String role_name) {
		super();
		this.id = id;
		this.role = role;
		this.role_name = role_name;
	}

	public Role(String role, String role_name) {
		super();
		this.role = role;
		this.role_name = role_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public String toString() {
		return "Role [getId()=" + getId() + ", getRole()=" + getRole() + ", getRole_name()=" + getRole_name() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((role_name == null) ? 0 : role_name.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (role_name == null) {
			if (other.role_name != null)
				return false;
		} else if (!role_name.equals(other.role_name))
			return false;
		return true;
	}
	

}
