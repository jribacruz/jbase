package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author jcruz
 *
 */
public class SelectStatement extends AbstractJPQLFragment {

	private List<EntityAttribute> attributes = new ArrayList<EntityAttribute>();

	private String alias;

	private Class<?> entity;

	public SelectStatement(Class<?> entity, String alias) {
		super();
		this.entity = entity;
		this.alias = alias;
	}

	@Override
	public String buildJPQLFragment() {
		if (this.attributes.isEmpty()) {
			return replace("select %s from %s %s", this.alias, this.entity.getSimpleName(), this.alias);
		}
		return replace("select new %s(%s) from %s %s", this.entity.getSimpleName(), joinAttributes(), this.entity.getSimpleName(),
				this.alias);
	}

	private String joinAttributes() {
		List<String> attributeNames = new ArrayList<String>();
		for (EntityAttribute attribute : attributes) {
			attributeNames.add(attribute.getName());
		}
		return StringUtils.join(attributeNames, ",");
	}

	public String buildCountJPQLFragment() {
		return null;
	}

	public List<EntityAttribute> getAttributes() {
		return attributes;
	}

}
