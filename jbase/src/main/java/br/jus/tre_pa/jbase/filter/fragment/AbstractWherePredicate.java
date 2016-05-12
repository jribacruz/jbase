package br.jus.tre_pa.jbase.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um predicado da clausula where.
 * 
 * @author jcruz
 *
 */
public abstract class AbstractWherePredicate extends AbstractJPQLFragment {

	/**
	 * Atributo do predicado
	 * 
	 * {@link EntityAttribute}
	 */
	private EntityAttribute attribute;

	/**
	 * Operador do predicado.
	 * 
	 * {@link OperatorType}
	 */
	private OperatorType operatorType;

	public AbstractWherePredicate(EntityAttribute attribute, OperatorType operatorType) {
		super();
		this.attribute = attribute;
		this.operatorType = operatorType;
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public EntityAttribute getAttribute() {
		return attribute;
	}

}
