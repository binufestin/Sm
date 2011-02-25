package org.np.stoman.ajax.interfaze.impl;

import static org.np.stoman.dao.support.FieldConstants.NAME;
import static org.np.stoman.dao.support.HibernateSupport.getHibernateSupport;
import static org.np.stoman.dao.support.Restrict.EQ;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.np.stoman.ajax.interfaze.Vendor;
import org.np.stoman.dao.support.CriteriaBuilder;
import org.np.stoman.persistence.Vendors;

public class VendorImpl extends BaseImpl implements Vendor {

	private static final Logger logger = Logger.getLogger(VendorImpl.class);

	@Override
	public Vendors create(Map<String, String> params) throws Exception {
		Vendors vendor = get(params);
		if(vendor.getVendorId() == null) {
			getHibernateSupport().save(vendor);
		} else {
			getHibernateSupport().evict(vendor);
			getHibernateSupport().evict(vendor.getAddresses());
			Vendors web = Vendors.class.newInstance();
			BeanUtils.populate(web, params); //TODO: Optimization
			Integer id = vendor.getVendorId();
			Integer aId = vendor.getAddresses().getAddressId();
			BeanUtils.copyProperties(vendor, web);
			vendor.setVendorId(id);
			vendor.getAddresses().setAddressId(aId);
			getHibernateSupport().save(vendor);
		}
		return vendor;
	}
	
	@Override
	public Vendors get(Map<String, String> params) throws Exception {
		Vendors vendor = Vendors.class.newInstance();
		BeanUtils.populate(vendor, params);
		CriteriaBuilder cb = new CriteriaBuilder(Vendors.class);
		cb.getCriteria().setFetchMode("addresses", FetchMode.JOIN);
		List<Vendors> vendors = getHibernateSupport().get(cb.getCriteria(),
				 EQ.restrict(new Object[] { NAME, vendor.getName()}));
		if(vendors.isEmpty()) {
			return vendor;
		}
		return vendors.get(0);
	}

}
