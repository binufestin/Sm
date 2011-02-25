package org.np.stoman.ajax.interfaze;

import java.util.Map;

import org.np.stoman.persistence.Vendors;

public interface Vendor extends AjaxMarker {
	Vendors create(Map<String, String> params) throws Exception;
	Vendors get(Map<String, String> params) throws Exception;
}
