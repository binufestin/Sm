package org.np.stoman.persistence;

// Generated 17 Oct, 2010 12:55:11 PM by Hibernate Tools 3.2.2.GA

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * PurchaseOrders generated by hbm2java
 */
public class PurchaseOrders implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5365337266942387054L;
	private Integer purchaseOrderId;
	private BigDecimal amount;
	private BigDecimal discount;
	private Set<PurchaseMaterials> purchaseMaterialses = new HashSet<PurchaseMaterials>(
			0);
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public PurchaseOrders() {
	}

	public PurchaseOrders(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public PurchaseOrders(Integer purchaseOrderId, BigDecimal amount,
			BigDecimal discount, Set<PurchaseMaterials> purchaseMaterialses) {
		this.purchaseOrderId = purchaseOrderId;
		this.amount = amount;
		this.discount = discount;
		this.purchaseMaterialses = purchaseMaterialses;
	}

	public Integer getPurchaseOrderId() {
		return this.purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Set<PurchaseMaterials> getPurchaseMaterialses() {
		return this.purchaseMaterialses;
	}

	public void setPurchaseMaterialses(
			Set<PurchaseMaterials> purchaseMaterialses) {
		this.purchaseMaterialses = purchaseMaterialses;
	}

}
