package br.jus.tre_pa.jbase.groovy.filter.fragment;

import javax.persistence.Query
import javax.persistence.TemporalType

/**
 * Representa um parametro do tipo Date de um predicado binário.
 * 
 * @author jcruz
 *
 */
class WhereDatePredicateParam extends AbstractWherePredicateParam {

	public WhereDatePredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super(paramIndex, predicate);
	}

	@Override
	public void setupQueryParam(Query query) {
		query.setParameter(predicate.attribute.name, predicate.attribute as Date, TemporalType.TIMESTAMP);
	}

}
