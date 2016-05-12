package br.jus.tre_pa.jbase.filter.fragment;

import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * 
 * @author jcruz
 *
 */
public class WhereDatePredicateParam extends AbstractWherePredicateParam {

	public WhereDatePredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super(paramIndex, predicate);
	}

	@Override
	public String buildJPQLFragment() {
		return replace("?%d", this.getParamIndex());
	}

	@Override
	public void buildQueryParam(Query query) {
		query.setParameter(getPredicate().getAttribute().getName(), getPredicate().getAttribute().getValueAsDate(), TemporalType.TIMESTAMP);
	}

}
