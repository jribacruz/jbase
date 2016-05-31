package br.jus.tre_pa.jbase.groovy.filter;

import java.io.Serializable;


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
	 * Inicializa os parâmetros da query.
	 * 
	 * @param q
	 */
	//void setupQueryParams(Query q);
}
