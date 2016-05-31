package br.jus.tre_pa.jbase.groovy.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um predicado binário (exp op exp) da clausula where.
 * 
 * @author jcruz
 *
 */
class WhereBinaryPredicate extends AbstractWherePredicate {
	/**
	 * Paramentro da clausula binária.
	 */
	AbstractWherePredicateParam param;

	WhereBinaryPredicate(EntityAttribute attribute, OperatorType operatorType) {
		super(attribute, operatorType);
	}

	/**
	 * Monta o predicado binário.
	 */
	@Override
	String buildJPQLFragment() {
		if (attribute.type == String.class) {
			return " ${attribute.name} ${operatorType.representation} ${param.buildJPQLFragment()} ";
		}
		return " ${attribute.name} ${operatorType.representation} ${param.buildJPQLFragment()} ";
	}
}
