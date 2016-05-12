package br.jus.tre_pa.jbase.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * @author jcruz
 *
 */
public class WhereUnaryPredicate extends AbstractWherePredicate {

	public WhereUnaryPredicate(EntityAttribute attribute, OperatorType operatorType) {
		super(attribute, operatorType);
	}

	@Override
	public String buildJPQLFragment() {
		return replace("%s %s", getAttribute().getName(), getOperatorType().getRepresentation());
	}

}
