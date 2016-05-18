package br.jus.tre_pa.jbase.filter;

import java.io.Serializable;

import javax.persistence.Query;

/**
 * 
 * @author jcruz
 *
 */
public interface JPAQueryBuilder extends Serializable {

	/**
	 * Retorna a string da query baseada no filtro.
	 * 
	 * @return
	 */
	String buildStringQuery();

	/**
	 * 
	 * @param q
	 */
	void setupQueryParams(Query q);
}
