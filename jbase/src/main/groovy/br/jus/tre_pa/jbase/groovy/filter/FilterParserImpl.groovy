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
		prepareSelectStatement(statement, filter)
		preparePathStatement(statement, filter)
		prepareWhereStatement(statement, filter)
		prepareOrderByStatement(statement, filter)

		return statement
	}

	/**
	 * Prepara a região SELECT da JPQL.
	 * 
	 * @param statement
	 * @param filter
	 */
	private <F extends Filterable>void prepareSelectStatement(JPQLStatement statement, F filter) {
		SelectStatement selectStatement = new SelectStatement()
		Filter annotationFilter = filter.class.getAnnotation(Filter.class)

		selectStatement.alias = annotationFilter.alias()
		selectStatement.entityClass = annotationFilter.entityClass()

		annotationFilter.projection().each { attr ->
			selectStatement.attributes << new EntityAttribute(name: attr)
		}

		statement.selectStatement = selectStatement
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
		WhereStatement whereStatement = new WhereStatement()

		def notNullFields =  {Field field -> field.get(filter) != null}

		/* Itera sobre todos os atributos do filtro cujo valores sejam diferentes de null */
		filter.class.declaredFields.every(notNullFields).each { Field field ->
			Class<?> fieldType = field.type

			if(fieldType == String.class) {
			}
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
