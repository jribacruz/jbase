package br.jus.tre_pa.jbase.groovy.filter;

import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement;

/**
 *
 * 
 * @author jcruz
 *
 */
public interface FilterParser {

	/**
	 * Transforma um Filter em um fragment de uma consulta JPQL filtrada.
	 *
	 * @param filter
	 * @return
	 */
	<F extends Filterable> JPQLStatement parse(F filter);
}
