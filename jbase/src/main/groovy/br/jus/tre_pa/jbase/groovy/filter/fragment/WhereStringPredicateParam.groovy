package br.jus.tre_pa.jbase.groovy.filter.fragment;

import javax.persistence.Query;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um paramentro do tipo String de um predicado da clausula WHERE.
 * 
 * @author jcruz
 *
 */
class WhereStringPredicateParam extends AbstractWherePredicateParam {

	WhereStringPredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super(paramIndex, predicate);
	}

	@Override
	void setupQueryParam(Query query) {
		if (predicate.operatorType.equals(OperatorType.LIKE) || predicate.operatorType.equals(OperatorType.NOT_LIKE)) {
			query.setParameter(predicate.attribute.name, "%${(predicate.attribute as String).toUpperCase()}%");
			return;
		}
		query.setParameter(predicate.attribute.name, (predicate.attribute as String).toUpperCase());
	}

}
