package br.jus.tre_pa.jbase.filter.enums;

/**
 * 
 * @author jcruz
 *
 */
public enum OperatorType implements Operator {
	/**
	 * 
	 */
	IS_TRUE("is true"),
	/**
	 * 
	 */
	IS_NOT_TRUE("is not true"),
	/**
	 * 
	 */
	IS_FALSE("is false"),
	/**
	 * 
	 */
	IS_NOT_FALSE("is not false"),
	/**
	 * 
	 */
	IS_NULL("is null"),
	/**
	 * 
	 */
	IS_NOT_NULL("is not null"),
	/**
	 * 
	 */
	EQUAL("="),
	/**
	 * 
	 */
	NOT_EQUAL("<>"),
	/**
	 * 
	 */
	GREATER_THAN(">"),
	/**
	 * 
	 */
	GREATER_THAN_OR_EQUAL_TO(">="),
	/**
	 * 
	 */
	LESS_THAN("<"),
	/**
	 * 
	 */
	LESS_THAN_OR_EQUAL_TO("<="),
	/**
	 * 
	 */
	LIKE("like"),
	/**
	 * 
	 */
	NOT_LIKE("not like"),
	/**
	 * 
	 */
	IN("in"),
	/**
	 * 
	 */
	NOT_IN("not in"),

	/**
	 * 
	 */
	DEFAULT("");

	private String representation;

	private OperatorType(String representation) {
		this.representation = representation;
	}

	@Override
	public String getRepresentation() {
		return this.representation;
	}

}
