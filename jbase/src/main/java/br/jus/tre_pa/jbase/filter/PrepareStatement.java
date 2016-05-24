package br.jus.tre_pa.jbase.filter;

import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement;

/**
 * 
 * @author jcruz
 *
 */
public interface PrepareStatement {

	/**
	 * 
	 * @param statement
	 * @param filter
	 */
	<F extends Filterable> void prepare(JPQLStatement statement, F filter);
}
