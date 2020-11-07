package com.addressbookrestassured;

import java.sql.Date;

public class AddressBookData {

	private String first_name;
	private String last_name;
	private String address;
	private String city;
	private String state;
	private int zip;
	private long phone_number;
	private String email_id;
	private String addressbook_name;
	private String addressbook_type;
	private Date date;
	public AddressBookData(String first_name, String last_name, String address, String city, String state,
			int zip, long phone_number, String email_id, String addressbook_name, String addressbook_type,Date date) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone_number = phone_number;
		this.email_id = email_id;
		this.addressbook_name = addressbook_name;
		this.addressbook_type = addressbook_type;
		this.date=date;
	}
	public AddressBookData(String first_name, String last_name, String address, String city, String state,
			int zip, long phone_number, String email_id, String addressbook_name, String addressbook_type) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone_number = phone_number;
		this.email_id = email_id;
		this.addressbook_name = addressbook_name;
		this.addressbook_type = addressbook_type;
	}
	public AddressBookData(String first_name, String last_name, String address, String city, String state,
			int zip, long phone_number, String email_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone_number = phone_number;
		this.email_id = email_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "AddressBookData [first_name=" + first_name + ", last_name=" + last_name + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phone_number=" + phone_number
				+ ", email_id=" + email_id + ", addressbook_name=" + addressbook_name + ", addressbook_type="
				+ addressbook_type + "]";
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getAddressbook_name() {
		return addressbook_name;
	}
	public void setAddressbook_name(String addressbook_name) {
		this.addressbook_name = addressbook_name;
	}
	public String getAddressbook_type() {
		return addressbook_type;
	}
	public void setAddressbook_type(String addressbook_type) {
		this.addressbook_type = addressbook_type;
	}
}