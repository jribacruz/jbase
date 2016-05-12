package br.jus.tre_pa.jbase.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * @author jcruz
 *
 */
public abstract class AbstractWherePredicate extends AbstractJPQLFragment {

	private EntityAttribute attribute;

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
