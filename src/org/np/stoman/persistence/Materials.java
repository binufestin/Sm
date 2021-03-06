package org.np.stoman.persistence;

// Generated 17 Oct, 2010 12:55:11 PM by Hibernate Tools 3.2.2.GA

import java.util.Set;

/**
 * Materials generated by hbm2java
 */
public class Materials implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7481348609711418886L;
	private Integer materialId;
	private String name;
	private Integer sensitivity;
	private Integer importance;

	public Materials() {
	}

	public Materials(Integer materialId, String name, Integer sensitivity,
			Integer importance) {
		this.materialId = materialId;
		this.name = name;
		this.sensitivity = sensitivity;
		this.importance = importance;
	}

	public Materials(Integer materialId, String name, Integer sensitivity,
			Integer importance,
			Set<VendorMaterialArchives> vendorMaterialArchiveses,
			Set<VendorMaterials> vendorMaterialses) {
		this.materialId = materialId;
		this.name = name;
		this.sensitivity = sensitivity;
		this.importance = importance;

	}

	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSensitivity() {
		return this.sensitivity;
	}

	public void setSensitivity(Integer sensitivity) {
		this.sensitivity = sensitivity;
	}

	public Integer getImportance() {
		return this.importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

}
