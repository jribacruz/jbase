package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Representa os caminhos (Joins) da JPQL.
 * 
 * @author jcruz
 *
 */
class PathStatement extends AbstractJPQLFragment {

	/**
	 * Lista de caminhos.
	 */
	List<PathExpression> paths = new ArrayList<PathExpression>();

	/**
	 * Monta os caminhos JPQL (Joins).
	 */
	@Override
	String buildJPQLFragment() {
		paths.collect { p ->  p.buildJPQLFragment() }.join('')
	}
}
