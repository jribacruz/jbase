package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.util.Reflections;
import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.filter.FilterContext;
import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

public class FilterPageBean<T, F extends Filterable<T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FilterContext filterContext;

	@Inject
	private UIService service;

	private F filterBean;

	private Class<F> filterBeanClass;

	public void clearFilter() {

	}

	public String filter() {
		filterContext.active(filterBean);
		return null;
	}

	public F getFilterBean() {
		if (this.filterBean == null) {
			this.filterBean = Reflections.instantiate(getFilterBeanClass());
		}
		return filterBean;
	}

	protected Class<F> getFilterBeanClass() {
		if (this.filterBeanClass == null) {
			this.filterBeanClass = Reflections.getGenericTypeArgument(this.getClass(), 1);
		}

		return this.filterBeanClass;
	}

	private String getEventKey() {
		return Strings.camelCaseToSymbolSeparated(this.getClass().getSuperclass().getSimpleName(), ".");
	}

}
