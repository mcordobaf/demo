package com.example.demo_data.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHONES")
public class PhoneModel {

	@Id
	private String number;
	@Id
	private String citycode;
	@Id
	private String countrycode;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = true)
	private UserModel user;

	public PhoneModel() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	
	
}
