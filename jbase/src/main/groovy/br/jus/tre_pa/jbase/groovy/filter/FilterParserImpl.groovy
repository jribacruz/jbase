package br.jus.tre_pa.jbase.groovy.filter

import br.jus.tre_pa.jbase.filter.FilterParser
import br.jus.tre_pa.jbase.filter.Filterable
import br.jus.tre_pa.jbase.filter.annotation.Filter
import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.SelectStatement

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
	public <F extends Filterable> JPQLStatement parser(F filter) {

		JPQLStatement statement = new JPQLStatement()



		return statement
	}

	/**
	 * 
	 * @param statement
	 * @param filter
	 */
	private <F extends Filterable>void prepareSelectStatement(JPQLStatement statement, F filter) {
		SelectStatement selectStatement = new SelectStatement()
		Filter annotationFilter = filter.class.getAnnotation(Filter.class)

		selectStatement.alias = annotationFilter.alias()
	}

	/**
	 * 
	 * @param statement
	 * @param filter
	 */
	private <F extends Filterable>void preparePathStatement(JPQLStatement statement, F filter) {
	}

	/**
	 * 
	 * @param statement
	 * @param filter
	 */
	private <F extends Filterable>void prepareWhereStatement(JPQLStatement statement, F filter) {

		/* Itera sobre todos os atributos do filtro */
		filter.class.declaredFields.each { field ->
		}
	}

	/**
	 * 
	 * @param statement
	 * @param filter
	 */
	private <F extends Filterable>void prepareOrderByStatement(JPQLStatement statement, F filter) {
	}
}
