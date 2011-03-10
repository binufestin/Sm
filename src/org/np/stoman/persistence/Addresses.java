package org.np.stoman.persistence;

// Generated 17 Oct, 2010 12:55:11 PM by Hibernate Tools 3.2.2.GA

import java.util.Set;

/**
 * Addresses generated by hbm2java
 */
public class Addresses implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442599137462036920L;
	private int addressId;
	private String streetName1;
	private String streetName2;
	private String area;
	private String city;
	private Integer zip;
	private String state;
	private String country;
	private String phone;
	private String mobile;
	private String fax;
	private String email;

	public Addresses() {
	}

	public Addresses(int addressId, String streetName1) {
		this.addressId = addressId;
		this.streetName1 = streetName1;
	}

	public Addresses(int addressId, String streetName1, String streetName2,
			String area, String city, Integer zip, String state,
			String country, Set<Vendors> vendorses, Set<Users> userses) {
		this.addressId = addressId;
		this.streetName1 = streetName1;
		this.streetName2 = streetName2;
		this.area = area;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetName1() {
		return this.streetName1;
	}

	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}

	public String getStreetName2() {
		return this.streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZip() {
		return this.zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
