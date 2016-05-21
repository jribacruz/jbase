package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;
import java.util.List;

import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Show;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBody;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateFooter;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateHeader;

public abstract class AbstractSingleSelectionDialogPageBean<T, R> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<R> source;

	private T parentBean;
	
	private R selectedBean;

	private Class<T> parentBeanClass;

	protected abstract List<R> handleSource();

	@Show
	@UpdateHeader
	@UpdateBody
	@UpdateFooter
	public String load(T parentBean) {
		this.setParentBean(parentBean);
		return null;
	}

	@Hide
	public String done() {
		return null;
	}

	protected void clear() {
		this.source = null;
	}

	protected void setParentBean(T parentBean) {
		this.parentBean = parentBean;
	}

	public T getParentBean() {
		if (parentBean == null) {
			parentBean = Reflections.instantiate(this.getParentBeanClass());
		}
		return parentBean;
	}

	protected Class<T> getParentBeanClass() {
		if (this.parentBeanClass == null) {
			this.parentBeanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.parentBeanClass;
	}

	public List<R> getSource() {
		if (this.source == null) {
			this.source = handleSource();
		}
		return source;
	}

	public R getSelectedBean() {
		return selectedBean;
	}

	public void setSelectedBean(R selectedBean) {
		this.selectedBean = selectedBean;
	}
	
	

}
