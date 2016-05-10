package br.jus.tre_pa.jbase.filter.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import br.jus.tre_pa.jbase.filter.Filterable;

public class JPQLBuilder {

	private FilterModel model;

	private StringBuilder jpql = new StringBuilder();

	private Map<String, String> jpqlParams;

	public <T, F extends Filterable<T>> String build(F filter) {
		this.model = new FilterParser().parser(filter);
		createJPQLParams();
		buildSelect();
		buildPaths();
		buildWhere();
		buildOrderBy();

		return jpql.toString();
	}

	private void createJPQLParams() {
		this.jpqlParams = new HashMap<String, String>();
		this.jpqlParams.put("alias", model.getAlias());
		this.jpqlParams.put("entity.class.name", model.getEntityClassName());
		this.jpqlParams.put("projection.attributes", attributeListToString(model.getProjectionAttributes()));
		this.jpqlParams.put("orderby.attributes", attributeListToString(model.getOrderByAttributes()));

	}

	private void buildSelect() {
		if (!model.getProjectionAttributes().isEmpty()) {
			buildJPQLFragment("select new ${entity.class.name}(${projection.attributes}) from ${entity.class.name} ${alias} ");
			return;
		}
		buildJPQLFragment("select ${alias} from ${entity.class.name} ${alias} ");
	}

	private void buildPaths() {

	}

	private void buildWhere() {

	}

	private void buildOrderBy() {
		if (!model.getOrderByAttributes().isEmpty()) {
			buildJPQLFragment("order by ${orderby.attributes} ");
		}
	}

	private void buildJPQLFragment(String jpqlFragment) {
		jpql.append(new StrSubstitutor(jpqlParams).replace(jpqlFragment));
	}

	private String attributeListToString(List<String> attributes) {
		return StringUtils.join(attributes, ",");
	}
}
