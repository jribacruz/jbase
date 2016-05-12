package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Representa os caminhos (Joins) da JPQL.
 * 
 * @author jcruz
 *
 */
public class PathStatement extends AbstractJPQLFragment {

	/**
	 * Lista de caminhos.
	 */
	private List<PathExpression> paths = new ArrayList<PathExpression>();

	@Override
	public String buildJPQLFragment() {
		for (PathExpression pathExpression : paths) {
			getFragments().add(pathExpression.buildJPQLFragment());
		}
		return joinPaths();
	}

	public List<PathExpression> getPaths() {
		return paths;
	}

	private String joinPaths() {
		return StringUtils.join(getFragments(), "\n");
	}

}