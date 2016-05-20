package br.jus.tre_pa.jbase.filter.implementation;

import javax.enterprise.context.SessionScoped;

import br.jus.tre_pa.jbase.filter.FilterContext;
import br.jus.tre_pa.jbase.filter.Filterable;

@SessionScoped
public class FilterContextImpl implements FilterContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object filterBean;

	@Override
	public <F extends Filterable> void active(F filterBean) {
		this.filterBean = filterBean;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <F extends Filterable> F getActive() {
		return (F) filterBean;
	}

}
