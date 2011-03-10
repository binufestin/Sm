package org.np.stoman.ajax.interfaze;

import java.util.Map;

import org.np.stoman.persistence.Ranks;
import org.np.stoman.persistence.VendorMaterials;

public interface Vendor extends AjaxMarker {
	Object create(Map<String, String> params) throws Exception;

	Object get(Map<String, String> params) throws Exception;

	Object getMaterials(String name);

	VendorMaterials createVendorMaterial(Integer vmId, String material,
			Float price);

	boolean deleteVendorMaterial(Integer vmId);

	public Ranks getRank(Integer vId);

	public Ranks updateRank(Integer vId, Integer rankVal);
}
