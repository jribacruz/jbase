package br.jus.tre_pa.jbase.groovy.filter.enums;

/**
 * 
 * @author jcruz
 *
 */
public enum OperatorType implements Operator {
	/**
	 * 
	 */
	IS_TRUE("is true", true),
	/**
	 * 
	 */
	IS_NOT_TRUE("is not true", true),
	/**
	 * 
	 */
	IS_FALSE("is false", true),
	/**
	 * 
	 */
	IS_NOT_FALSE("is not false", true),
	/**
	 * 
	 */
	IS_NULL("is null", true),
	/**
	 * 
	 */
	IS_NOT_NULL("is not null", true),
	/**
	 * 
	 */
	EQUAL("=", false),
	/**
	 * 
	 */
	NOT_EQUAL("<>", false),
	/**
	 * 
	 */
	GREATER_THAN(">", false),
	/**
	 * 
	 */
	GREATER_THAN_OR_EQUAL_TO(">=", false),
	/**
	 * 
	 */
	LESS_THAN("<", false),
	/**
	 * 
	 */
	LESS_THAN_OR_EQUAL_TO("<=", false),
	/**
	 * 
	 */
	LIKE("like",false),
	/**
	 * 
	 */
	NOT_LIKE("not like", false),
	/**
	 * 
	 */
	IN("in", false),
	/**
	 * 
	 */
	NOT_IN("not in", false),

	/**
	 * 
	 */
	DEFAULT("", false);

	private boolean unary;

	private String representation;

	private OperatorType(String representation, boolean unary) {
		this.representation = representation;
		this.unary = unary;
	}

	@Override
	public String getRepresentation() {
		return this.representation;
	}

	public boolean isUnary() {
		return unary;
	}

}
