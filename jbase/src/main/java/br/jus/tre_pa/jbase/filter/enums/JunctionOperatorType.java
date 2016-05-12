package br.jus.tre_pa.jbase.filter.enums;

/**
 * 
 * @author jcruz
 *
 */
public enum JunctionOperatorType implements Operator {
	AND(" and "), OR(" or ");

	private String representation;

	private JunctionOperatorType(String representation) {
		this.representation = representation;
	}

	@Override
	public String getRepresentation() {
		return this.representation;
	}
}
