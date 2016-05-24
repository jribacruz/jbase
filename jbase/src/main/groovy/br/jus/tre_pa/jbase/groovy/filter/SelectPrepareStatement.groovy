package br.jus.tre_pa.jbase.groovy.filter

import br.jus.tre_pa.jbase.filter.Filterable
import br.jus.tre_pa.jbase.filter.PrepareStatement
import br.jus.tre_pa.jbase.filter.annotation.Filter
import br.jus.tre_pa.jbase.groovy.filter.fragment.EntityAttribute
import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.SelectStatement

/**
 * 
 * @author jcruz
 *
 */
class SelectPrepareStatement implements PrepareStatement{

	SelectStatement selectStatement = new SelectStatement()
	
	@Override
	public <F extends Filterable> void prepare(JPQLStatement statement, F filter) {
		Filter annotationFilter = filter.class.getAnnotation(Filter.class)

		selectStatement.alias = annotationFilter.alias()
		selectStatement.entityClass = annotationFilter.entityClass()

		annotationFilter.projection().each { attr ->
			selectStatement.attributes << new EntityAttribute(name: attr)
		}

		statement.selectStatement = selectStatement
	}
}
