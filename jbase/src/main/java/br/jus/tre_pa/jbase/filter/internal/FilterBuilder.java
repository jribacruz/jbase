package br.jus.tre_pa.jbase.filter.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

public class FilterBuilder {

	private FilterBeanModel model;

	/**
	 * 
	 */
	private StringBuilder jpql;

	/**
	 * 
	 */
	private Map<String, String> jpqlParams = new HashMap<String, String>();

	/**
	 * 
	 */
	private Map<String, String> jpqlFragments = new HashMap<String, String>();

	public FilterBuilder() {
		super();
		this.jpql = new StringBuilder();
	}

	public String buildJPQL(Object bean) {
		this.model = new FilterBeanModel(bean);
		this.createJPQLParams();
		this.createJPQLFragments();

		this.buildSelectFragmentJPQL();
		this.buildPathFragmentJPQL();
		this.buildWhereFragmentJPQL();
		this.buildOrderByFragmentJPQL();
		return this.jpql.toString();
	}

	/**
	 * Monta a parte do 'select' do JPQL.
	 */
	protected void buildSelectFragmentJPQL() {
		// Verifica se o FilterModel possui lista de projeções.
		if (model.hasProjectionAttributes()) {
			// Monta um select com o construtor da entidade dado a lista de atributos de projeção.
			buildFragment("select.constructor");
			return;
		}
		// Monta um select padrão (ex: select x from X)
		buildFragment("select.default");
	}

	/**
	 * Monta a parte dos 'joins', se existir, do JPQL.
	 */
	protected void buildPathFragmentJPQL() {
		for (Map<String, String> path : model.getPaths()) {
			for (Map.Entry<String, String> entry : path.entrySet()) {
				Map<String, String> pathMap = new HashMap<String, String>();
				pathMap.put("path", entry.getValue());
				pathMap.put("path.alias", entry.getKey());
				buildFragment("path", pathMap);
			}
		}
	}

	/**
	 * Monta a parte do 'order by' do JPQL.
	 */
	protected void buildOrderByFragmentJPQL() {
		if (model.hasOrderByAttributes()) {
			buildFragment("orderby");
		}
	}

	/**
	 * 
	 */
	protected void buildWhereFragmentJPQL() {

	}

	/**
	 * Monta os parâmentros que serão substituidos nos fragmentos.
	 */
	private void createJPQLParams() {
		jpqlParams.put("entity.name", model.getEntityName());
		jpqlParams.put("alias", model.getAlias());
		jpqlParams.put("projection.attributes", model.getProjectionAttributesAsString());
		jpqlParams.put("order.attributes", model.getOrderByAttributesAsString());
	}

	/**
	 * 
	 */
	private void createJPQLFragments() {
		jpqlFragments.put("select.default", "select ${alias} from ${entity.name} ${alias} ");
		jpqlFragments.put("select.constructor", "select new ${entity.name}(${projection.attributes}) from ${entity.name} ${alias} ");
		jpqlFragments.put("path", "left join ${path} ${path.alias} ");
		jpqlFragments.put("orderby", "order by ${order.attributes}");
	}

	private void buildFragment(String jpqlFragment) {
		jpql.append(new StrSubstitutor(jpqlParams).replace(jpqlFragments.get(jpqlFragment)));
	}

	private void buildFragment(String jpqlFragment, Map<String, String> params) {
		jpql.append(new StrSubstitutor(params).replace(jpqlFragments.get(jpqlFragment)));
	}
}
