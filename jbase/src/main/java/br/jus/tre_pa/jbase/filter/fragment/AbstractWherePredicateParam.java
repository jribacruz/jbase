package br.jus.tre_pa.jbase.filter.fragment;

import javax.persistence.Query;

/**
 * Representa um parâmentro de um predicado (binário) da clausura where.
 * 
 * @author jcruz
 *
 */
public abstract class AbstractWherePredicateParam extends AbstractJPQLFragment {

	/**
	 * Índice do paramentro.
	 */
	private int paramIndex;

	/**
	 * Predicado da clausula WHERE.
	 */
	private AbstractWherePredicate predicate;

	public AbstractWherePredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super();
		this.paramIndex = paramIndex;
		this.predicate = predicate;
	}

	/**
	 * 
	 */
	@Override
	public String buildJPQLFragment() {
		return replace("?%d", this.getParamIndex());
	}

	/**
	 * 
	 * @param query
	 */
	public abstract void setupQueryParam(Query query);

	public AbstractWherePredicate getPredicate() {
		return predicate;
	}

	public void setPredicate(AbstractWherePredicate predicate) {
		this.predicate = predicate;
	}

	public int getParamIndex() {
		return paramIndex;
	}

}
