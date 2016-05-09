package br.jus.tre_pa.jbase.filter;

/**
 * 
 * @author jcruz
 *
 */
public enum OperatorType {
	/**
	 * 
	 */
	IS_TRUE("${attr} is true "),
	/**
	 * 
	 */
	IS_NOT_TRUE("${attr} is not true "),
	/**
	 * 
	 */
	IS_FALSE("$${attr} is false "),
	/**
	 * 
	 */
	IS_NOT_FALSE("${attr} is not false "),
	/**
	 * 
	 */
	IS_NULL("${attr} is null "),
	/**
	 * 
	 */
	IS_NOT_NULL("${attr} is not null "),
	/**
	 * 
	 */
	EQUAL("${attr} = ${value} "),
	/**
	 * 
	 */
	NOT_EQUAL("${attr} <> ${value} "),
	/**
	 * 
	 */
	GREATER_THAN("${attr} > ${value} "),
	/**
	 * 
	 */
	GREATER_THAN_OR_EQUAL_TO("${attr} >= ${value} "),
	/**
	 * 
	 */
	LESS_THAN("${attr} < ${value} "),
	/**
	 * 
	 */
	LESS_THAN_OR_EQUAL_TO("${attr} <= ${value} "),
	/**
	 * 
	 */
	LIKE("upper(${attr}) like '%${value}%' "),
	/**
	 * 
	 */
	LIKE_LEFT("upper(${attr}) like '%${value}' "),
	/**
	 * 
	 */
	LIKE_RIGHT("upper(${attr}) like '${value}%' "),
	/**
	 * 
	 */
	NOT_LIKE("upper(${attr}) not like '%${value}%' "),
	/**
	 * 
	 */
	IN("${attr} in (${value}) "),
	/**
	 * 
	 */
	NOT_IN("${attr} not in (${value}) ");

	private String expression;

	private OperatorType(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

}
