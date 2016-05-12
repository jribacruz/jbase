package br.jus.tre_pa.jbase.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.JoinType;

/**
 * 
 * @author jcruz
 *
 */
public class PathExpression extends AbstractJPQLFragment {

	private String path;

	private String alias;

	private JoinType joinType;

	public PathExpression(String path, String alias, JoinType joinType) {
		super();
		this.path = path;
		this.alias = alias;
		this.joinType = joinType;
	}

	@Override
	public String buildJPQLFragment() {
		return replace("%s %s %s", joinType.getRepresentation(), path, alias);
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public String getPath() {
		return path;
	}

	public String getAlias() {
		return alias;
	}

}
