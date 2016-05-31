package br.jus.tre_pa.jbase.groovy.filter.fragment;

import javax.persistence.Query;

/**
 * Representa um parâmentro de um predicado (binário) da clausura where.
 * 
 * @author jcruz
 *
 */
abstract class AbstractWherePredicateParam extends AbstractJPQLFragment {

	/**
	 * Índice do paramentro.
	 */
	int paramIndex;

	/**
	 * Predicado da clausula WHERE.
	 */
	AbstractWherePredicate predicate;

	AbstractWherePredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super();
		this.paramIndex = paramIndex;
		this.predicate = predicate;
	}

	/**
	 * 
	 */
	@Override
	String buildJPQLFragment() {
		return "?${paramIndex}";
	}

	/**
	 * 
	 * @param query
	 */
	abstract void setupQueryParam(Query query);

}
