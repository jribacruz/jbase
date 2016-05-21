package br.jus.tre_pa.jbase.groovy.filter

import java.lang.reflect.Field

import br.jus.tre_pa.jbase.filter.FilterParser
import br.jus.tre_pa.jbase.filter.Filterable
import br.jus.tre_pa.jbase.filter.annotation.Filter
import br.jus.tre_pa.jbase.groovy.filter.fragment.EntityAttribute
import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.SelectStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.WhereStatement

/**
 * 
 * @author jcruz
 *
 */
class FilterParserImpl implements FilterParser {

	/*
	 * 
	 */
	@Override
	public <F extends Filterable> JPQLStatement parse(F filter) {

		JPQLStatement statement = new JPQLStatement()
		new SelectPrepareStatement().prepare(statement, filter)
		new PathPrepareStatement().prepare(statement, filter)
		new WherePrepareStatement().prepare(statement, filter)
		new OrderByPrepareStatement().prepare(statement, filter)
		return statement
	}
}
