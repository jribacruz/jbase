package br.jus.tre_pa.jbase.filter.fragment;

import javax.persistence.Query;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * 
 * @author jcruz
 *
 */
public abstract class AbstractWherePredicateParam extends AbstractJPQLFragment {

	private AbstractWherePredicate predicate;

	public AbstractWherePredicateParam(AbstractWherePredicate predicate) {
		super();
		this.predicate = predicate;
	}

	public abstract void buildQueryParam(Query query);

	public AbstractWherePredicate getPredicate() {
		return predicate;
	}

	public void setPredicate(AbstractWherePredicate predicate) {
		this.predicate = predicate;
	}

}
