package br.jus.tre_pa.jbase.jsf.layout;

import java.util.HashMap;

public class UILayoutMap extends HashMap<String, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Boolean get(Object key) {
		if (!this.containsKey(key)) {
			this.put((String) key, true);
		}
		return super.get(key);
	}
}
