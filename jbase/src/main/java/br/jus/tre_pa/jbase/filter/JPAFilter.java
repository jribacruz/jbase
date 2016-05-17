package br.jus.tre_pa.jbase.filter;

import java.io.Serializable;

import javax.persistence.Query;

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

	/**
	 * 
	 * @param q
	 */
	void setupQueryParams(Query q);
}
