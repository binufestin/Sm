package org.np.stoman.persistence;

// Generated 17 Oct, 2010 12:55:11 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * VendorMaterialArchives generated by hbm2java
 */
public class VendorMaterialArchives implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6953704743795079004L;
	private int vendorMaterialArchiveId;
	private Vendors vendors;
	private Materials materials;
	private Users users;
	private Date priceStartDate;
	private Date priceEndDate;
	private Date modifiedDate;
	private Float pricePerQty;

	public VendorMaterialArchives() {
	}

	public VendorMaterialArchives(int vendorMaterialArchiveId, Vendors vendors,
			Materials materials, Users users, Date modifiedDate) {
		this.vendorMaterialArchiveId = vendorMaterialArchiveId;
		this.vendors = vendors;
		this.materials = materials;
		this.users = users;
		this.modifiedDate = modifiedDate;
	}

	public VendorMaterialArchives(int vendorMaterialArchiveId, Vendors vendors,
			Materials materials, Users users, Date priceStartDate,
			Date priceEndDate, Date modifiedDate) {
		this.vendorMaterialArchiveId = vendorMaterialArchiveId;
		this.vendors = vendors;
		this.materials = materials;
		this.users = users;
		this.priceStartDate = priceStartDate;
		this.priceEndDate = priceEndDate;
		this.modifiedDate = modifiedDate;
	}

	public int getVendorMaterialArchiveId() {
		return this.vendorMaterialArchiveId;
	}

	public void setVendorMaterialArchiveId(int vendorMaterialArchiveId) {
		this.vendorMaterialArchiveId = vendorMaterialArchiveId;
	}

	public Vendors getVendors() {
		return this.vendors;
	}

	public void setVendors(Vendors vendors) {
		this.vendors = vendors;
	}

	public Materials getMaterials() {
		return this.materials;
	}

	public void setMaterials(Materials materials) {
		this.materials = materials;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getPriceStartDate() {
		return this.priceStartDate;
	}

	public void setPriceStartDate(Date priceStartDate) {
		this.priceStartDate = priceStartDate;
	}

	public Date getPriceEndDate() {
		return this.priceEndDate;
	}

	public void setPriceEndDate(Date priceEndDate) {
		this.priceEndDate = priceEndDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Float getPricePerQty() {
		return pricePerQty;
	}
	
	public void setPricePerQty(Float pricePerQty) {
		this.pricePerQty = pricePerQty;
	}

}
