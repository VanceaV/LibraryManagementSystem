package com.smoothstack.entity;

public class Borrower extends Entity {

	private String name;
	private String address;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Id=[" + getId() + "]" + " Borrower Name: " + name + " Borrower Address: " + address
				+ " Borrower Phone: " + phone;
	}

}
