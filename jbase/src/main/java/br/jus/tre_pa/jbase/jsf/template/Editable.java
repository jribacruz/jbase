package br.jus.tre_pa.jbase.jsf.template;

import br.jus.tre_pa.jbase.jsf.workflow.base.EventTargetBean;

/**
 * 
 * Métodos complementares ao CRUD de edit.
 * 
 * @author jcruz
 *
 * @param <T>
 * @param <I>
 */
public interface Editable<T, I> extends EventTargetBean<T> {

	/**
	 * 
	 * @return
	 */
	String save();

	/**
	 * 
	 * Realiza o carregamento de uma entidade pelo Id.
	 * 
	 * @param id
	 * @return
	 */
	String load(Long id);

	/**
	 * 
	 * @return
	 */
	String cancel();

	/**
	 * 
	 * Realiza a criação de um objeto.
	 * 
	 * @return
	 */
	String create();

	/**
	 * 
	 * @param id
	 * @return
	 */
	String fetch(I id);
}
