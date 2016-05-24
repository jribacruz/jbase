package br.jus.tre_pa.jbase.groovy.filter.fragment;

import br.jus.tre_pa.jbase.filter.enums.JoinType;

/**
 * Representa um de caminho (Join).
 * 
 * @author jcruz
 *
 */
class PathExpression extends AbstractJPQLFragment {

	/**
	 * Caminho
	 */
	String path;

	/**
	 * Alias do caminho.
	 */
	String alias;

	/**
	 * Tipo de join {@link JoinType}
	 */
	JoinType joinType;

	PathExpression(String path, String alias, JoinType joinType) {
		super();
		this.path = path;
		this.alias = alias;
		this.joinType = joinType;
	}

	/**
	 * Monta a declaração de caminhos (Joins).
	 */
	@Override
	String buildJPQLFragment() {
		" ${joinType.representation} ${path} ${alias} ";
	}
}
