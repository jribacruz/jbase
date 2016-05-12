package br.jus.tre_pa.jbase.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.jus.tre_pa.jbase.filter.enums.LogicalOperator;

/**
 * 
 * @author jcruz
 *
 */
public abstract class AbstractJPQLFragment {

	/**
	 * 
	 * @return
	 */
	private List<String> fragments = new ArrayList<String>();

	public abstract String buildJPQLFragment();

	protected String replace(String str, Object... params) {
		return String.format(str, params);
	}

	/**
	 * 
	 * @param logicalOperator
	 * @return
	 */
	protected <T> String joinFragments(LogicalOperator logicalOperator) {
		return StringUtils.join(this.fragments, logicalOperator.getRepresentation());
	}

	/**
	 * 
	 * @param preffix
	 * @param logicalOperator
	 * @return
	 */
	protected <T> String joinFragments(String preffix, LogicalOperator logicalOperator) {
		return preffix.concat(StringUtils.join(this.fragments, logicalOperator.getRepresentation()));
	}

	/**
	 * 
	 * @param preffix
	 * @param logicalOperator
	 * @param suffix
	 * @return
	 */
	protected <T> String joinFragments(String preffix, LogicalOperator logicalOperator, String suffix) {
		return preffix.concat(StringUtils.join(this.fragments, logicalOperator.getRepresentation())).concat(suffix);
	}

	public List<String> getFragments() {
		return fragments;
	}

}
