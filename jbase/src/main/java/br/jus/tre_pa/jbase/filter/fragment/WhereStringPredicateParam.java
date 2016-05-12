package br.jus.tre_pa.jbase.filter.fragment;

import javax.persistence.Query;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * @author jcruz
 *
 */
public class WhereStringPredicateParam extends AbstractWherePredicateParam {

	public WhereStringPredicateParam(AbstractWherePredicate predicate) {
		super(predicate);
	}

	@Override
	public String buildJPQLFragment() {
		return replace(":%s", getPredicate().getAttribute().getName());
	}

	@Override
	public void buildQueryParam(Query query) {
		if (getPredicate().getOperatorType().equals(OperatorType.LIKE) || getPredicate().getOperatorType().equals(OperatorType.NOT_LIKE)) {
			query.setParameter(getPredicate().getAttribute().getName(),
					"%" + getPredicate().getAttribute().getValueAsString().toUpperCase() + "%");
			return;
		}
		query.setParameter(getPredicate().getAttribute().getName(), getPredicate().getAttribute().getValueAsString().toUpperCase());
	}

}
