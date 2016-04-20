package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;

public abstract class AbstractListDialogPageBean<T, I> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PaginationContext paginationContext;

	private List<T> resultList;

	private T bean;

	private Class<T> beanClass;

	public abstract String load(I id);

	protected abstract List<T> handleResultList();

	@Hide
	public void cancel() {
		this.clear();
	}

	public void clear() {
		this.resultList = null;
	}

	public List<T> getResultList() {
		if (this.resultList == null) {
			this.resultList = handleResultList();
		}

		return this.resultList;
	}

	protected Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}

	protected void setBean(T bean) {
		this.bean = bean;
	}

	public T getBean() {
		if (bean == null) {
			bean = Reflections.instantiate(this.getBeanClass());
		}
		return bean;
	}

	public Pagination getPagination() {
		return paginationContext.getPagination(getBeanClass(), true);
	}

}
