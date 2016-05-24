package br.jus.tre_pa.jbase.groovy.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um predicado da clausula where.
 * 
 * @author jcruz
 *
 */
abstract class AbstractWherePredicate extends AbstractJPQLFragment {

	/**
	 * Atributo do predicado
	 * 
	 * {@link EntityAttribute}
	 */
	EntityAttribute attribute;

	/**
	 * Operador do predicado.
	 * 
	 * {@link OperatorType}
	 */
	OperatorType operatorType;

	AbstractWherePredicate(EntityAttribute attribute, OperatorType operatorType) {
		super();
		this.attribute = attribute;
		this.operatorType = operatorType;
	}

}
