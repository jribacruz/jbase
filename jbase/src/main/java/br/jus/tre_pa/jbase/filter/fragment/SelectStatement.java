package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Representa uma seleção (SELECT) da JPQL.
 * 
 * @author jcruz
 *
 */
public class SelectStatement extends AbstractJPQLFragment {

	/**
	 * Declaração de SELECT padrão.
	 */
	private static String SELECT_DEFAULT_STATEMENT = "select %s from %s %s";

	/**
	 * Declaração de SELECT com construtor de classe.
	 */
	private static String SELECT_CONSTRUCTOR_STATEMENT = "select new %s(%s) from %s %s";

	/**
	 * Declaração de SELECT de contagem.
	 */
	private static String SELECT_COUNT_STATEMENT = "select new %s(%s) from %s %s";

	/**
	 * Lista de atributos da projeção (Usados para a montagem da declaração de SELECT com construtor de classe).
	 */
	private List<EntityAttribute> attributes = new ArrayList<EntityAttribute>();

	/**
	 * Alias da clausula FROM.
	 */
	private String alias;

	/**
	 * Entidade da clausula FROM.
	 */
	private Class<?> entity;

	public SelectStatement(Class<?> entity, String alias) {
		super();
		this.entity = entity;
		this.alias = alias;
	}

	/**
	 * 
	 */
	@Override
	public String buildJPQLFragment() {
		if (this.attributes.isEmpty()) {
			return replace(SELECT_DEFAULT_STATEMENT, this.alias, this.entity.getSimpleName(), this.alias);
		}
		return replace(SELECT_CONSTRUCTOR_STATEMENT, this.entity.getSimpleName(), joinAttributes(), this.entity.getSimpleName(),
				this.alias);
	}

	/**
	 * 
	 * @return
	 */
	public String buildCountJPQLFragment() {
		return replace(SELECT_COUNT_STATEMENT, this.alias, this.entity.getSimpleName(), this.alias);
	}

	private String joinAttributes() {
		List<String> attributeNames = new ArrayList<String>();
		for (EntityAttribute attribute : attributes) {
			attributeNames.add(attribute.getName());
		}
		return StringUtils.join(attributeNames, ",");
	}

	public List<EntityAttribute> getAttributes() {
		return attributes;
	}

}
