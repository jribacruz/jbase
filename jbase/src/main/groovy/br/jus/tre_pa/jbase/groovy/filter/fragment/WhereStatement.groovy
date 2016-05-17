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
	public String buildJPQLFragment() {
		predicateExpressions.collect { p -> p.buildJPQLFragment() }.join(junctionOperator.name())
	}
}
