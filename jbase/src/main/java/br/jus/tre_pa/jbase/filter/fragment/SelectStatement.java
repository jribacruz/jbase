package br.jus.tre_pa.jbase.filter.fragment;

import java.util.List;

/**
 * 
 * @author jcruz
 *
 */
public class SelectStatement extends AbstractJPQLFragment {

	private List<EntityAttribute> attributes;

	@Override
	public String buildJPQLFragment() {
		return null;
	}

	public String buildCountJPQLFragment() {
		return null;
	}

	public List<EntityAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<EntityAttribute> attributes) {
		this.attributes = attributes;
	}

}
