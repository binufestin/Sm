package org.np.stoman.ajax.interfaze.impl;

import static org.np.stoman.dao.support.FieldConstants.NAME;
import static org.np.stoman.dao.support.HibernateSupport.getHibernateSupport;
import static org.np.stoman.dao.support.Restrict.EQ;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.np.stoman.ajax.interfaze.Vendor;
import org.np.stoman.dao.support.CriteriaBuilder;
import org.np.stoman.persistence.Materials;
import org.np.stoman.persistence.Users;
import org.np.stoman.persistence.VendorMaterials;
import org.np.stoman.persistence.Vendors;

public class VendorImpl extends BaseImpl implements Vendor {

	private static final Logger logger = Logger.getLogger(VendorImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public Object create(Map<String, String> params) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Vendors vendor = (Vendors) ((Map<String, Object>) get(params))
				.get("vendor");
		if (vendor.getVendorId() == null) {
			getHibernateSupport().save(vendor);
		} else {
			getHibernateSupport().evict(vendor);
			getHibernateSupport().evict(vendor.getAddresses());
			Vendors web = Vendors.class.newInstance();
			BeanUtils.populate(web, params); // TODO: Optimization
			Integer id = vendor.getVendorId();
			Integer aId = vendor.getAddresses().getAddressId();
			BeanUtils.copyProperties(vendor, web);
			vendor.setVendorId(id);
			vendor.getAddresses().setAddressId(aId);
			getHibernateSupport().save(vendor);
		}
		data.put("vendor", vendor);
		CriteriaBuilder cb = new CriteriaBuilder(VendorMaterials.class);
		List<VendorMaterials> vms = getHibernateSupport()
				.get(cb.getCriteria(),
						EQ.restrict(new Object[] { "vendors.id",
								vendor.getVendorId() }));
		data.put("vms", vms);
		return data;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object get(Map<String, String> params) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Vendors vendor = Vendors.class.newInstance();
		BeanUtils.populate(vendor, params);
		CriteriaBuilder cb = new CriteriaBuilder(Vendors.class);
		cb.getCriteria().setFetchMode("addresses", FetchMode.JOIN);
		List<Vendors> vendors = getHibernateSupport().get(cb.getCriteria(),
				EQ.restrict(new Object[] { NAME, vendor.getName() }));
		if (vendors.isEmpty()) {
			data.put("vendor", vendor);
			data.put("vms", new ArrayList<VendorMaterials>());
			return data;
		}
		data.put("vendor", vendors.get(0));
		cb = new CriteriaBuilder(VendorMaterials.class);
		List<VendorMaterials> vms = getHibernateSupport().get(
				cb.getCriteria(),
				EQ.restrict(new Object[] { "vendors.id",
						vendors.get(0).getVendorId() }));
		data.put("vms", vms);
		return data;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object getMaterials(String name) {
		CriteriaBuilder cb = new CriteriaBuilder(Materials.class);
		List<Materials> materials = getHibernateSupport().get(cb.getCriteria(),
				EQ.restrict(new Object[] { NAME, name }));
		if (materials.isEmpty())
			return new Materials();
		return materials.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public VendorMaterials createVendorMaterial(Integer vId, String material,
			Float price) {
		Materials m = null;
		VendorMaterials vm = null;
		Users user = getHibernateSupport().get(Users.class, 1);
		Date today = new Date();

		CriteriaBuilder cb = new CriteriaBuilder(VendorMaterials.class);
		List<VendorMaterials> vms = getHibernateSupport()
				.get(cb.getCriteria(),
						EQ.restrict(new Object[] { cb.wrap("vendors.id"), vId }),
						EQ.restrict(new Object[] { cb.wrap("materials.name"),
								material }));
		if (vms.isEmpty()) {
			vm = new VendorMaterials();
			vm.setUsers(user);
			vm.setModifiedDate(today);
			vm.setPricePerQty(price);
			Vendors v = getHibernateSupport().get(Vendors.class, vId);
			vm.setVendors(v);
			m = (Materials) getMaterials(material);
			if (m.getMaterialId() == null) {
				m.setName(material);
				getHibernateSupport().save(m);
			}
			vm.setMaterials(m);
			vm.setPriceStartDate(today);
		} else {
			vm = vms.get(0);
			vm.setPricePerQty(price);
			vm.setPriceStartDate(today);
			vm.setUsers(user);
			vm.setModifiedDate(today);
		}
		getHibernateSupport().save(vm);
		return vm;
	}

	@Override
	public boolean deleteVendorMaterial(Integer vmId) {
		VendorMaterials vm = getHibernateSupport().get(VendorMaterials.class,
				vmId);
		getHibernateSupport().delete(vm);
		return true;
	}
}
