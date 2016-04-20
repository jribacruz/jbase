package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	private Map<I, Boolean> selection = new HashMap<I, Boolean>();

	public abstract String load(I id);

	protected abstract List<T> handleResultList();

	@Hide
	public void cancel() {
		this.clear();
	}

	@Hide
	public String done() {
		return null;
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

	public Map<I, Boolean> getSelection() {
		return selection;
	}

	public void setSelection(Map<I, Boolean> selection) {
		this.selection = selection;
	}

	public void clearSelection() {
		this.selection = new HashMap<I, Boolean>();
	}

	public List<I> getSelectedList() {
		List<I> selectedList = new ArrayList<I>();
		Iterator<I> iter = getSelection().keySet().iterator();
		while (iter.hasNext()) {
			I id = iter.next();
			if (getSelection().get(id)) {
				selectedList.add(id);
			}
		}
		return selectedList;
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
