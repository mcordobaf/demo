package com.example.demo_data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "USERS")
public class UserModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7510340190961723560L;

	@Id
	private String id;
	
	private String name;
	private String email;
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<PhoneModel> phones;
	
	private Date created;
	private Date modified;
	@Column(name = "is_active")
	private boolean isActive;
	
	private String token;
	
	@Column(name = "last_login")
	private Date lastLogin;
	
	public UserModel() {
		super();
		
		if (StringUtils.isEmpty(id))
			this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneModel> getPhones() {
		if (phones == null)
			phones = new ArrayList<PhoneModel>();
		return phones;
	}

	public void setPhones(List<PhoneModel> phones) {
		this.phones = phones;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
