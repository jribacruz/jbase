package br.jus.tre_pa.jbase.filter.fragment;

import javax.persistence.Query;

/**
 * 
 * 
 * @author jcruz
 *
 */
public abstract class AbstractWherePredicateParam extends AbstractJPQLFragment {

	private int paramIndex;

	private AbstractWherePredicate predicate;

	public AbstractWherePredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super();
		this.paramIndex = paramIndex;
		this.predicate = predicate;
	}

	public abstract void buildQueryParam(Query query);

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
