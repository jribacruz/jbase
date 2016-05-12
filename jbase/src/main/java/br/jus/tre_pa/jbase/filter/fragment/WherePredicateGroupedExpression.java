package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import br.jus.tre_pa.jbase.filter.enums.JunctionOperatorType;

/**
 * 
 * @author jcruz
 *
 */
public class WherePredicateGroupedExpression extends WherePredicateExpression {

	private List<AbstractWherePredicate> predicates = new ArrayList<AbstractWherePredicate>();

	private JunctionOperatorType junctionOperator = JunctionOperatorType.OR;

	@Override
	public String buildJPQLFragment() {
		for (AbstractWherePredicate predicate : predicates) {
			getFragments().add(predicate.buildJPQLFragment());
		}
		return joinFragments("(", junctionOperator, ")");
	}

	public List<AbstractWherePredicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<AbstractWherePredicate> predicates) {
		this.predicates = predicates;
	}

	public JunctionOperatorType getJunctionOperator() {
		return junctionOperator;
	}

	public void setJunctionOperator(JunctionOperatorType junctionOperator) {
		this.junctionOperator = junctionOperator;
	}

}
