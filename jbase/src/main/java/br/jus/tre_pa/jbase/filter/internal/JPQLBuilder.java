package br.jus.tre_pa.jbase.filter.internal;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.expression.impl.JQuerySelectorExpressionResolver;

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
		this.jpqlParams.put("projection.attributes", JPQLBuilderHelper.getProjectionAttributesAsString(model.getProjectionAttributes()));
		this.jpqlParams.put("orderby.attributes", JPQLBuilderHelper.getOrderByAttributesAsString(model.getOrderByAttributes()));

	}

	private void buildSelect() {
		if (!model.getProjectionAttributes().isEmpty()) {
			jpql.append(JPQLBuilderHelper
					.replace("select new ${entity.class.name}(${projection.attributes}) from ${entity.class.name} ${alias} ", jpqlParams));
			return;
		}
		jpql.append(JPQLBuilderHelper.replace("select ${alias} from ${entity.class.name} ${alias} ", jpqlParams));
	}

	private void buildPaths() {

	}

	private void buildWhere() {

	}

	private void buildOrderBy() {
		if(!model.getOrderByAttributes().isEmpty()) {
			jpql.append(JPQLBuilderHelper.replace("order by ${orderby.attributes} ", jpqlParams));
		}
	}
}
