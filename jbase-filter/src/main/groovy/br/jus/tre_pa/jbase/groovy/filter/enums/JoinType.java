package br.jus.tre_pa.jbase.groovy.filter.enums;

/**
 * 
 * @author jcruz
 *
 */
public enum JoinType implements Operator {
	/** Inner join. */
	INNER("inner join"),

	/** Left outer join. */
	LEFT("left join"),

	/** Right outer join. */
	RIGHT("right join");

	private String representation;

	private JoinType(String representation) {
		this.representation = representation;
	}

	@Override
	public String getRepresentation() {
		return this.representation;
	}

}
