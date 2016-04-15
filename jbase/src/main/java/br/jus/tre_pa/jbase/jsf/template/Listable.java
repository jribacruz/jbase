package br.jus.tre_pa.jbase.jsf.template;

import br.jus.tre_pa.jbase.jsf.workflow.base.EventTargetBean;

/**
 * 
 * @author jcruz
 *
 * @param <T>
 */
public interface Listable<T> extends EventTargetBean<T> {

	/**
	 * 
	 * @return
	 */
	String refresh();

	/**
	 * 
	 * @return
	 */
	String delete();

}
