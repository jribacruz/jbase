package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import br.jus.tre_pa.jbase.filter.enums.JunctionOperatorType;

/**
 * 
 * @author jcruz
 *
 */
class WherePredicateExpression extends AbstractJPQLFragment {

	List<AbstractWherePredicate> predicates = new ArrayList<AbstractWherePredicate>();

	JunctionOperatorType junctionOperator = JunctionOperatorType.AND;

	@Override
	public String buildJPQLFragment() {
		predicates.collect {p -> p.buildJPQLFragment()}.join(junctionOperator.name())
	}
}
