package com.customerking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Customers")
public class Customers {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_sequence")
	@GenericGenerator(name = "id_sequence", strategy = 
			"com.customerking.model.CustomerIdSequenceGenerator")
	private Long id;

	@Column
	private String name;

	@Column
	private String email;
	
	@Column
	private String address;
	
	@Column
	private String gender;
	
	@Column
	private String dob;
	
	@Column
	private String profession;
	
	@Column
	private Boolean isCustomerActive = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Boolean getIsCustomerActive() {
		return isCustomerActive;
	}

	public void setIsCustomerActive(Boolean isCustomerActive) {
		this.isCustomerActive = isCustomerActive;
	}
	
	
}
