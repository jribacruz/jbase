package br.jus.tre_pa.jbase.groovy.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um predicado unário da clausula WHERE.
 * 
 * @author jcruz
 *
 */
class WhereUnaryPredicate extends AbstractWherePredicate {

	WhereUnaryPredicate(EntityAttribute attribute, OperatorType operatorType) {
		super(attribute, operatorType);
	}

	@Override
	public String buildJPQLFragment() {
		return " ${attribute.name} ${operatorType.representation} ";
	}

}
