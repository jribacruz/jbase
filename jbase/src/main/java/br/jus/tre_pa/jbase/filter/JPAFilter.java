package br.jus.tre_pa.jbase.filter;

import java.io.Serializable;

/**
 * 
 * @author jcruz
 *
 */
public interface JPAFilter extends Serializable {

	/**
	 * Retorna a string da query baseada no filtro.
	 * 
	 * @return
	 */
	String createStringQuery();
}
