package org.np.stoman.ajax.interfaze;

import java.util.List;
import java.util.Map;

import org.np.stoman.persistence.VendorMaterials;

public interface PurchaseOrder extends AjaxMarker {

	Map generate(Map<String, String> map);
	String save();
}
