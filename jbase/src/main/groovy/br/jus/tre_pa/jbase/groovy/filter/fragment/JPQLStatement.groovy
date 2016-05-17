package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import br.jus.tre_pa.jbase.filter.enums.JPQLStatementType;

/**
 * Representa uma declaração JPQL completa.
 * 
 * @author jcruz
 *
 */
class JPQLStatement extends AbstractJPQLFragment {

	/**
	 * 
	 */
	SelectStatement selectStatement = new SelectStatement();

	/**
	 * 
	 */
	PathStatement pathStatement = new PathStatement();

	/**
	 * 
	 */
	WhereStatement whereStatement = new WhereStatement();

	/**
	 * 
	 */
	OrderByStatement orderByStatement = new OrderByStatement();

	JPQLStatement(SelectStatement selectStatement) {
		super();
		this.selectStatement = selectStatement;
	}

	/**
	 * 
	 */
	@Override
	String buildJPQLFragment() {
		return "${selectStatement.buildJPQLFragment()} ${pathStatement.buildJPQLFragment()} ${whereStatement.buildJPQLFragment()} ${orderByStatement.buildJPQLFragment()}"
	}

	/**
	 * 
	 * @return
	 */
	String buildCountJPQLFragment() {
		return "${selectStatement.buildCountJPQLFragment()} ${pathStatement.buildJPQLFragment()} ${whereStatement.buildJPQLFragment()}"
	}
}
