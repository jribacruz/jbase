package br.jus.tre_pa.jbase.filter.internal;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class FilterBuilder {

	private FilterBeanModel model;

	private StringBuilder sql;

	public FilterBuilder() {
		super();
		this.sql = new StringBuilder();
	}

	public String buildSQL(Object bean) {
		this.model = new FilterBeanModel(bean);
		this.buildSelectSQL();
		this.buildPathSQL();
		this.buildWhereSQL();
		this.buildOrderBySQL();
		return this.sql.toString();
	}

	/**
	 * 
	 */
	protected void buildSelectSQL() {
		String entityName = model.getEntityName();
		String alias = model.getAlias();

		if (model.getProjectionAttributes().isEmpty()) {
			Object[] params = { alias, entityName, alias };
			sql.append(String.format("select %s from %s %s ", params));
			return;
		}
		Object[] params = { entityName, StringUtils.join(model.getProjectionAttributes(), ","), entityName, alias };
		sql.append(String.format("select new %s(%s) from %s %s ", params));
		sql.append("\n");
	}

	/**
	 * 
	 */
	protected void buildPathSQL() {
		for (Map<String, String> path : model.getPaths()) {
			for (Map.Entry<String, String> entry : path.entrySet()) {
				sql.append(String.format("left join %s %s ", entry.getValue(), entry.getKey()));
				sql.append("\n");
			}
		}
	}

	/**
	 * 
	 */
	protected void buildWhereSQL() {

	}

	/**
	 * 
	 */
	protected void buildOrderBySQL() {
		if (!model.getOrderByAttributes().isEmpty()) {
			sql.append(String.format("order by %s ", StringUtils.join(model.getOrderByAttributes(), ",")));
			return;
		}
	}

}
