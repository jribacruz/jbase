package br.jus.tre_pa.jbase.filter;

import java.io.Serializable;

/**
 * 
 * @author jcruz
 *
 */
public interface FilterContext extends Serializable {

	/**
	 * 
	 * Ativa o filtro.
	 * 
	 * @param filterBean
	 */
	<T, F extends Filterable<T>> void active(F filterBean);

	/**
	 * Retorna o filtro ativo.
	 * 
	 * @return
	 */
	<T, F extends Filterable<T>> F getActive();
}
