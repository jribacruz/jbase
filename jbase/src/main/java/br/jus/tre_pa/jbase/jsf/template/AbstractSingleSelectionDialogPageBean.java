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

	private T bean;

	private Class<T> beanClass;

	protected abstract List<R> handleSource();

	@Show
	@UpdateHeader
	@UpdateBody
	@UpdateFooter
	public String load(T bean) {
		this.setBean(bean);
		return null;
	}

	@Hide
	public String done() {
		return null;
	}

	protected void clear() {
		this.source = null;
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

	protected Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}

	public List<R> getSource() {
		if (this.source == null) {
			this.source = handleSource();
		}
		return source;
	}

}
