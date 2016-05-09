package br.jus.tre_pa.jbase.filter.internal;

import javax.inject.Inject;

import br.jus.tre_pa.jbase.filter.FilterContext;
import br.jus.tre_pa.jbase.filter.JPAFilter;

public class JPAFilterImpl implements JPAFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FilterContext filterContext;

	@Override
	public String createStringQuery() {
		return null;
	}

}
