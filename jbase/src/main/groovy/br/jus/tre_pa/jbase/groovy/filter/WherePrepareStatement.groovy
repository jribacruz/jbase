package br.jus.tre_pa.jbase.groovy.filter

import java.lang.reflect.Field

import br.jus.tre_pa.jbase.filter.Filterable
import br.jus.tre_pa.jbase.filter.PrepareStatement
import br.jus.tre_pa.jbase.filter.annotation.FilterAttribute
import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.WherePredicateExpression
import br.jus.tre_pa.jbase.groovy.filter.fragment.WhereStatement
import br.jus.tre_pa.jbase.groovy.filter.fragment.WhereStringPredicateParam

/**
 * 
 * @author jcruz
 *
 */
class WherePrepareStatement implements PrepareStatement {

	WhereStatement whereStatement = new WhereStatement()

	int paramIndexCount = 1

	/**
	 * 
	 */
	@Override
	public <F extends Filterable> void prepare(JPQLStatement statement, F filter) {
		def notNullFields =  {Field field -> field.get(filter) != null}
		/* Itera sobre todos os atributos do filtro cujo valores sejam diferentes de null */
		filter.class.declaredFields.findAll(notNullFields).each { Field field ->
			Class<?> fieldType = field.type
			if(fieldType == String.class) {
				processStringField(field)
			}
			paramIndexCount++
		}
	}

	/* Processa atributo do tipo String */
	private void processStringField(Field f) {
		WherePredicateExpression exp = new WherePredicateExpression()
		
		

		//WhereStringPredicateParam param = new WhereStringPredicateParam(paramIndexCount, predicate)
	}

	private String extractAttributeName(Field f) {
		if(f.isAnnotationPresent(FilterAttribute.class)) {
			return f.getAnnotation(FilterAttribute.class).name()
		}
		return f.name
	}
}
