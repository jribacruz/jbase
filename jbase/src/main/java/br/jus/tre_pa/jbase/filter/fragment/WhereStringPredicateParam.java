package br.jus.tre_pa.jbase.filter.fragment;

import javax.persistence.Query;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um paramentro do tipo String de um predicado da clausula WHERE.
 * 
 * @author jcruz
 *
 */
public class WhereStringPredicateParam extends AbstractWherePredicateParam {

	public WhereStringPredicateParam(int paramIndex, AbstractWherePredicate predicate) {
		super(paramIndex, predicate);
	}

	@Override
	public void setupQueryParam(Query query) {
		if (getPredicate().getOperatorType().equals(OperatorType.LIKE) || getPredicate().getOperatorType().equals(OperatorType.NOT_LIKE)) {
			query.setParameter(getPredicate().getAttribute().getName(),
					"%" + getPredicate().getAttribute().getValueAsString().toUpperCase() + "%");
			return;
		}
		query.setParameter(getPredicate().getAttribute().getName(), getPredicate().getAttribute().getValueAsString().toUpperCase());
	}

}
