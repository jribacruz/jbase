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
	public <T, F extends Filterable<T>> void active(F filterBean) {
		this.filterBean = filterBean;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T, F extends Filterable<T>> F getActive() {
		return (F) filterBean;
	}

}
