package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import br.jus.tre_pa.jbase.filter.enums.JunctionOperatorType;

/**
 * 
 * @author jcruz
 *
 */
class WherePredicateGroupedExpression extends WherePredicateExpression {

	List<AbstractWherePredicate> predicates = new ArrayList<AbstractWherePredicate>();

	JunctionOperatorType junctionOperator = JunctionOperatorType.OR;

	@Override
	public String buildJPQLFragment() {
		def groupedExp = predicates.collect {p -> p.buildJPQLFragment()}.join(junctionOperator.name())
		return " (${groupedExp}) "
	}
}
