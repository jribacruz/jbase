package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Representa uma declaração OrderBy do JPQL.
 * 
 * @author jcruz
 *
 */
public class OrderByStatement extends AbstractJPQLFragment {

	/**
	 * 
	 */
	private List<String> orderByExpressions = new ArrayList<String>();

	/**
	 * Monta a declaração OrderBy.
	 */
	@Override
	public String buildJPQLFragment() {
		return replace(" %s ", joinOrderByExpressions());
	}

	public List<String> getOrderByExpressions() {
		return orderByExpressions;
	}

	private String joinOrderByExpressions() {
		return StringUtils.join(getOrderByExpressions(), ",");
	}

}
