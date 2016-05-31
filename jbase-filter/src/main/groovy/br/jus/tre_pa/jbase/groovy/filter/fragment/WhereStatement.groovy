package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import br.jus.tre_pa.jbase.filter.enums.JunctionOperatorType;

/**
 * 
 * Representa uma declaração WHERE do JPQL.
 * 
 * @author jcruz
 *
 */
class WhereStatement extends AbstractJPQLFragment {

	List<WherePredicateExpression> predicateExpressions = new ArrayList<WherePredicateExpression>();

	JunctionOperatorType junctionOperator = JunctionOperatorType.AND;

	@Override
	String buildJPQLFragment() {
		def wherePredicates = predicateExpressions.collect { it.buildJPQLFragment() }.join(junctionOperator.name())
		return predicateExpressions.isEmpty() ? "": " where ${wherePredicates} "
	}
}
