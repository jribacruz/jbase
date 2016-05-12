package br.jus.tre_pa.jbase.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * Representa um predicado binário (exp op exp) da clausula where.
 * 
 * @author jcruz
 *
 */
public class WhereBinaryPredicate extends AbstractWherePredicate {

	public WhereBinaryPredicate(EntityAttribute attribute, OperatorType operatorType) {
		super(attribute, operatorType);
	}

	/**
	 * Paramentro da clausula binária.
	 */
	private AbstractWherePredicateParam param;

	/**
	 * Monta o predicado binário.
	 */
	@Override
	public String buildJPQLFragment() {
		if (getAttribute().getType() == String.class) {
			return replace("upper(%s) %s %s", getAttribute().getName(), getOperatorType().getRepresentation(),
					getParam().buildJPQLFragment());
		}
		return replace("%s %s %s", getAttribute().getName(), getOperatorType().getRepresentation(), getParam().buildJPQLFragment());
	}

	public AbstractWherePredicateParam getParam() {
		return param;
	}

	public void setParam(AbstractWherePredicateParam param) {
		this.param = param;
	}

}
