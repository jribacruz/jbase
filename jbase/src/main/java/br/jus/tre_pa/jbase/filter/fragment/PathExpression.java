package br.jus.tre_pa.jbase.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.JoinType;

/**
 * Representa um de caminho (Join).
 * 
 * @author jcruz
 *
 */
public class PathExpression extends AbstractJPQLFragment {

	/**
	 * Caminho
	 */
	private String path;

	/**
	 * Alias do caminho.
	 */
	private String alias;

	/**
	 * Tipo de join {@link JoinType}
	 */
	private JoinType joinType;

	public PathExpression(String path, String alias, JoinType joinType) {
		super();
		this.path = path;
		this.alias = alias;
		this.joinType = joinType;
	}

	/**
	 * Monta a declaração de caminhos (Joins).
	 */
	@Override
	public String buildJPQLFragment() {
		return replace(" %s %s %s ", joinType.getRepresentation(), path, alias);
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
