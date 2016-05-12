package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import br.jus.tre_pa.jbase.filter.enums.JunctionOperatorType;

/**
 * 
 * @author jcruz
 *
 */
public class WhereStatement extends AbstractJPQLFragment {

	private List<WherePredicateExpression> predicateExpressions = new ArrayList<WherePredicateExpression>();

	private JunctionOperatorType junctionOperator = JunctionOperatorType.AND;

	@Override
	public String buildJPQLFragment() {
		for (WherePredicateExpression predicateExpression : getPredicateExpressions()) {
			getFragments().add(predicateExpression.buildJPQLFragment());
		}
		return joinFragments("where\n      ", junctionOperator);
	}

	public List<WherePredicateExpression> getPredicateExpressions() {
		return predicateExpressions;
	}

}
