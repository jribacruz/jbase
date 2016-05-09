package br.jus.tre_pa.jbase.filter.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

public class FilterBuilder {

	private FilterBeanModel model;

	private StringBuilder sql;

	Map<String, String> paramMap = new HashMap<String, String>();

	public FilterBuilder() {
		super();
		this.sql = new StringBuilder();
	}

	public String buildSQL(Object bean) {
		this.model = new FilterBeanModel(bean);
		this.createParams();

		this.buildSelectSQL();
		this.buildPathSQL();
		this.buildWhereSQL();
		this.buildOrderBySQL();
		return this.sql.toString();
	}

	private void createParams() {
		paramMap.put("entityName", model.getEntityName());
		paramMap.put("alias", model.getAlias());
		paramMap.put("projectionAttributes", model.getProjectionAttributesAsString());
		paramMap.put("orderAttributes", model.getOrderByAttributesAsString());
	}

	/**
	 * 
	 */
	protected void buildSelectSQL() {
		if (model.getProjectionAttributes().isEmpty()) {
			String selectSQL = "select ${alias} from ${entityName} ${alias} ";
			sql.append(new StrSubstitutor(paramMap).replace(selectSQL));
			return;
		}
		String selectSQL = "select new ${entityName}(${projectionAttributes}) from ${entityName} ${alias} ";
		sql.append(new StrSubstitutor(paramMap).replace(selectSQL));
		sql.append("\n");
	}

	/**
	 * 
	 */
	protected void buildPathSQL() {
		String pathSQL = "left join ${path} ${pathAlias} \n";
		for (Map<String, String> path : model.getPaths()) {
			for (Map.Entry<String, String> entry : path.entrySet()) {
				Map<String,String> pathMap = new HashMap<String, String>();
				pathMap.put("path", entry.getValue());
				pathMap.put("pathAlias", entry.getKey());
				sql.append(new StrSubstitutor(pathMap).replace(pathSQL));
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
			String orderBySQL = "order by ${orderAttributes} ";
			sql.append(new StrSubstitutor(paramMap).replace(orderBySQL));
		}
	}

}
