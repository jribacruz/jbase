package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;
import br.jus.tre_pa.jbase.jsf.workflow.base.EventTargetBean;

/**
 * 
 * 
 * @author jcruz
 *
 * @param <T>
 */
public abstract class AbstractEditFragmentDialogPageBean<T, I> implements Serializable, EventTargetBean<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T bean;

	private Class<T> beanClass;

	public abstract String load(I id);

	@Hide
	public String cancel() {
		return null;
	}

	public abstract String update();

	public T getBean() {
		if (bean == null) {
			bean = Reflections.instantiate(this.getBeanClass());
		}
		return bean;
	}

	protected void setBean(T bean) {
		this.bean = bean;
	}

	private Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}
		return this.beanClass;
	}
}
