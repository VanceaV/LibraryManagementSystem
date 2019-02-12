package com.smoothstack.entity;

public class Publisher extends Entity {

	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	@Override
	public String toString() {

		return "Id=[" + getId() + "]" + " publisherName is " + publisherName + " publisherAddress is "
				+ publisherAddress + " publisherPhone is " + publisherPhone;
	}

}
