package org.np.stoman.ajax.interfaze.impl;

import static org.np.stoman.dao.support.HibernateSupport.getHibernateSupport;
import static org.np.stoman.dao.support.Order.ASC;
import static org.np.stoman.dao.support.Restrict.EQ;
import static org.np.stoman.dao.support.Restrict.IN;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.FetchMode;
import org.np.stoman.ajax.interfaze.PurchaseOrder;
import org.np.stoman.dao.support.CriteriaBuilder;
import org.np.stoman.persistence.Addresses;
import org.np.stoman.persistence.Materials;
import org.np.stoman.persistence.PurchaseMaterials;
import org.np.stoman.persistence.PurchaseOrders;
import org.np.stoman.persistence.Ranks;
import org.np.stoman.persistence.Users;
import org.np.stoman.persistence.VendorMaterials;
import org.np.stoman.persistence.Vendors;

import sun.security.action.GetBooleanAction;

public class PurchaseOrderImpl extends BaseImpl implements PurchaseOrder {

	@Override
	@SuppressWarnings("unchecked")
	public Map<Integer, List<VendorMaterials>> generate(Map<String, String> map) {
		Map<Integer, List<VendorMaterials>> vMaterials = new HashMap<Integer, List<VendorMaterials>>();
		Date today = new Date();
		// make this dynamic from LDAP
		Users user = getHibernateSupport().get(Users.class, 1); // TODO:

		// First create new Materials and Vendors.
		for (String v : map.keySet()) {
			if (!v.startsWith("v_"))
				continue;

			Vendors vendor = new Vendors();
			vendor.setName(map.get(v));
			// Dummy Address. Let the user give a proper address when they wish.
			// TODO So, can we cache this address object?
			Addresses address = getHibernateSupport().get(Addresses.class, 1);
			vendor.setAddresses(address);
			getHibernateSupport().save(vendor);

			// Assign a default rank
			Ranks rank = new Ranks();
			rank.setModifiedDate(today);
			rank.setRank(0);
			rank.setUsers(user);
			rank.setVendors(vendor);
			getHibernateSupport().save(rank);

			String[] m = v.split("_");
			Materials material = new Materials();
			material.setName(m[1]);
			getHibernateSupport().save(material);

			VendorMaterials vm = new VendorMaterials();
			vm.setVendors(vendor);
			vm.setMaterials(material);
			vm.setModifiedDate(today);
			vm.setPriceStartDate(today);
			vm.setUsers(user);
			getHibernateSupport().save(vm);
		}

		for (String m : map.keySet()) {
			if (m.startsWith("v_"))
				continue;
			CriteriaBuilder cb = new CriteriaBuilder(VendorMaterials.class);
			cb.getCriteria().setFetchMode("vendors", FetchMode.JOIN);
			cb.getCriteria().setFetchMode("vendors.addresses", FetchMode.JOIN);
			List<VendorMaterials> vms = getHibernateSupport().get(
					cb.getCriteria(),
					EQ.restrict(new Object[] { cb.wrap("materials.name"), m }));

			Map<Integer, VendorMaterials> vIds = new HashMap<Integer, VendorMaterials>();
			for (VendorMaterials vm : vms)
				vIds.put(vm.getVendors().getVendorId(), vm);

			cb = new CriteriaBuilder(Ranks.class, 1, 1);

			List<Ranks> ranks = getHibernateSupport().get(
					cb.getCriteria(),
					ASC.order(new String[] { "rank" }),
					IN.restrict(new Object[] { cb.wrap("vendors.vendorId"),
							vIds.keySet() }));
			Vendors vChosen = ranks.get(0).getVendors();
			if (vMaterials.get(vChosen.getVendorId()) == null)
				vMaterials.put(vChosen.getVendorId(),
						new ArrayList<VendorMaterials>());
			vMaterials.get(vChosen.getVendorId()).add(
					vIds.get(vChosen.getVendorId()));
			VendorMaterials mCount = vIds.get(vChosen.getVendorId());
			mCount.setData(map.get(mCount.getMaterials().getName()));
			mCount.getVendors().getName(); // TODO: enable vendor fetching
		}
		getHTTPSession().setAttribute("poMaterials", vMaterials);
		return vMaterials;
	}

	@Override @SuppressWarnings("unchecked")
	public String save() {
		Map<Integer, List<VendorMaterials>> poMaterials = 
			(Map<Integer, List<VendorMaterials>>) getHTTPSession().getAttribute("poMaterials");
		PurchaseOrders po = new PurchaseOrders();
		Set<PurchaseMaterials> pms = new HashSet<PurchaseMaterials>();
		po.setPurchaseMaterialses(pms);
		Date today = Calendar.getInstance().getTime();
		for(Entry<Integer, List<VendorMaterials>> e : poMaterials.entrySet()) {
			for(VendorMaterials vm : e.getValue()) {
				PurchaseMaterials pm = new PurchaseMaterials();
				pm.setCreatedBy(1); //TODO: User
				pm.setDate(today);
				pm.setOrderedQuantity(Integer.valueOf(vm.getData()));
				pm.setPurchaseOrders(po);
				pms.add(pm);
				pm.setVendorMaterials(vm);
			}
		}
		getHibernateSupport().save(po);
		return SUCCESS + po.getPurchaseOrderId();
	}
	
}
