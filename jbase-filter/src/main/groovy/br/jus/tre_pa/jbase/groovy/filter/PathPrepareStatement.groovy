package br.jus.tre_pa.jbase.groovy.filter

import br.jus.tre_pa.jbase.filter.Filterable
import br.jus.tre_pa.jbase.filter.PrepareStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement

/**
 * 
 * @author jcruz
 *
 */
class PathPrepareStatement implements PrepareStatement {
	/**
	 * 
	 */
	@Override
	public <F extends Filterable> void prepare(JPQLStatement statement, F filter) {
	}
}
