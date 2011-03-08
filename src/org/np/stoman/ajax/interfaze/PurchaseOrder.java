package org.np.stoman.ajax.interfaze;

import java.util.List;
import java.util.Map;

import org.np.stoman.persistence.PurchaseMaterials;
import org.np.stoman.persistence.PurchaseOrders;
import org.np.stoman.persistence.VendorMaterials;

public interface PurchaseOrder extends AjaxMarker {

	PurchaseOrders generate(PurchaseOrders po, List<PurchaseMaterials> pms,
			Map<String, VendorMaterials> pmLinker);

	VendorMaterials getVendorMaterial(String vendor, String material);

	PurchaseOrders fetch(Integer poId);
}
