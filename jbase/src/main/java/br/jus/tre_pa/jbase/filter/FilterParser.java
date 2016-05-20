package br.jus.tre_pa.jbase.filter;

import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement;

/**
 * 
 * @author jcruz
 *
 */
public interface FilterParser {
	/**
	 * 
	 * @param filter
	 * @return
	 */
	<F extends Filterable> JPQLStatement parser(F filter);
}
