package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Representa uma declaração OrderBy do JPQL.
 * 
 * @author jcruz
 *
 */
class OrderByStatement extends AbstractJPQLFragment {

	/**
	 * 
	 */
	List<String> orderByExpressions = new ArrayList<String>();

	/**
	 * Monta a declaração OrderBy.
	 */
	@Override
	String buildJPQLFragment() {
		return "${orderByExpressions.join(',')}";
	}
}
