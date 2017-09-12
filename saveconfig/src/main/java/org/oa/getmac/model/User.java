/*
 * class for mapping table "users"
 */
package org.oa.getmac.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.oa.getmac.modelTDO.DTOUser;

@Entity
@Table(name = "users")
@XmlRootElement(name = "user")
public class User {
	@Id
	@Column(name = "username", length = 45)
	private String username;
	@Column(name = "password", length = 45)
	private String password;
	@Column(name = "enabled", columnDefinition = "TINYINT(4)")
	private Boolean enable;
	
	@XmlElement
	@ManyToMany
	@JoinTable(name="user_roles", 
	                joinColumns={@JoinColumn(name="username")}, 
	                inverseJoinColumns={@JoinColumn(name="role")})
	private Set<Role> roles = new HashSet<Role>();	
	
	@Transient
	private Set<Role> rightRoles = new HashSet<Role>();
	
	public User(){
		
	}

	public User(String username, String password, Boolean enable, Set<Role> roles, Set<Role> rightRoles) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.roles = roles;
		this.rightRoles = rightRoles;
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

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}


	public DTOUser getDTOUserForTable(){
		DTOUser dtoUser = new DTOUser();
		dtoUser.setId(this.getUsername());
		dtoUser.setUsername(this.getUsername());
		return dtoUser;
	}
	
	public void setDTOUser(DTOUser dtoUser){
		this.setUsername(dtoUser.getUsername());
		this.setPassword(dtoUser.getPassword());
		this.setEnable(dtoUser.getEnable());
		this.setRoles(dtoUser.getRoles());
		this.setRightRoles(dtoUser.getRightRoles());
		if ((dtoUser.getPassword() == null) || dtoUser.getPassword().isEmpty()){
			this.setPassword(null);
		}else{
			this.setPassword(dtoUser.getPassword());
		}		
	}

	
	public DTOUser getDTOUser(){
		DTOUser dtoUser = new DTOUser();
		
		dtoUser.setUsername(this.getUsername());
		dtoUser.setEnable(this.getEnable());
		
		
		if ((this.getPassword() == null)|| this.getPassword().isEmpty()){
			dtoUser.setPassword("Is Empty");	
		}else{
			dtoUser.setPassword("Is not Empty");
		}
		dtoUser.setRoles(this.getRoles());
		dtoUser.setRightRoles(this.getRightRoles());
		
		return dtoUser;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getEnable()="
				+ getEnable() 
				+ ", getRoles()=" + getRoles() + "]";
	}



	public Set<Role> getRightRoles() {
		return rightRoles;
	}

	public void setRightRoles(Set<Role> rightRoles) {
		this.rightRoles = rightRoles;
	}

}
