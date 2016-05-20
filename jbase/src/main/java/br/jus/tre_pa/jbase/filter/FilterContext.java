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
	<F extends Filterable> void active(F filterBean);

	/**
	 * Retorna o filtro ativo.
	 * 
	 * @return
	 */
	<F extends Filterable> F getActive();
}
