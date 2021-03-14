package com.customerking;

public class CustomerDTO {

	private Long id;
	
	private String name;
	
	private String email;
	
	private String address;
	
	private String gender;
	
	private String dob;
	
	private String profession;
	
	private Boolean isCustomerActive;

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
