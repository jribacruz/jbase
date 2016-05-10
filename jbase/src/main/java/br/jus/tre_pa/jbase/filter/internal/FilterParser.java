package br.jus.tre_pa.jbase.filter.internal;

import java.lang.reflect.Field;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;
import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * @author jcruz
 *
 */
public class FilterParser {

	private FilterModel model;

	/**
	 * 
	 * @param filter
	 * @return
	 */
	<T, F extends Filterable<T>> FilterModel parser(F filter) {
		this.model = new FilterModel();
		processFilterClass(filter);
		return this.model;
	}

	/**
	 * Processa a classe do filtro.
	 * 
	 * @param filter
	 */
	private <T, F extends Filterable<T>> void processFilterClass(F filter) {
		processFilterClassAnnotation(filter);
		processFilterClassAttributes(filter);
	}

	/**
	 * Processa a annotation @Filter da classe do filtro.
	 * 
	 * @param filter
	 */
	private <T, F extends Filterable<T>> void processFilterClassAnnotation(F filter) {
		Class<?> filterClass = filter.getClass();
		extractFilterClassEntityClassName(filter);
		extractFilterClassAnnotationProjectionAttribute(filterClass);
		extractFilterClassAnnotationOrderByAttribute(filterClass);
		extractFilterClassAnnotationAliasAttribute(filterClass);
		extractFilterClassAnnotationPathsAttribute(filterClass);
	}

	private <T, F extends Filterable<T>> void extractFilterClassEntityClassName(F filter) {
		String entityClassName = FilterParserHelper.<T, F> getEntityClass(filter).getSimpleName();
		model.setEntityClassName(entityClassName);
	}

	/**
	 * Processa o atributo projection:String[] da annotation @Filter da classe do filtro.
	 * 
	 * @param filterClass
	 */
	private void extractFilterClassAnnotationProjectionAttribute(Class<?> filterClass) {
		String[] projectionAttributeArray = filterClass.getAnnotation(Filter.class).projection();
		for (String projectionAttribute : projectionAttributeArray) {
			if (!Strings.isEmpty(projectionAttribute)) {
				model.getProjectionAttributes().add(projectionAttribute);
			}
		}
	}

	private void extractFilterClassAnnotationAliasAttribute(Class<?> filterClass) {
		String alias = filterClass.getAnnotation(Filter.class).alias();
		model.setAlias(alias);
	}

	private void extractFilterClassAnnotationPathsAttribute(Class<?> filterClass) {

	}

	/**
	 * Processa o atributo orderBy:String[] da annotation @Filter da classe do filtro.
	 * 
	 * @param filterClass
	 */
	private void extractFilterClassAnnotationOrderByAttribute(Class<?> filterClass) {
		String[] orderByAttributeArray = filterClass.getAnnotation(Filter.class).orderBy();
		for (String orderByAttribute : orderByAttributeArray) {
			if (!Strings.isEmpty(orderByAttribute)) {
				model.getOrderByAttributes().add(orderByAttribute);
			}
		}
	}

	/**
	 * Processa os atributos da classe do filtro.
	 * 
	 * @param filter
	 */
	private <T, F extends Filterable<T>> void processFilterClassAttributes(F filter) {
		String alias = FilterParserHelper.getAlias(filter);
		for (Field field : FilterParserHelper.getAttributesAsField(filter)) {
			processStringTypeAttribute(field, alias);
			processBooleanTypeAttribute(field, alias);
			processLongTypeAttribute(field, alias);
			processBigDecimalTypeAttribute(field, alias);
			processIntegerTypeAttribute(field, alias);
			processDateTypeAttribute(field, alias);
			processListTypeAttribute(field, alias);
			processMapTypeAttribute(field, alias);
		}
	}

	/**
	 * Processa os atributos do filtro do tipo String.
	 */
	private void processStringTypeAttribute(Field field, String alias) {
		if (FilterParserHelper.isStringTypeField(field)) {
			OperatorType operatorType = FilterParserHelper.isDefaultOperator(field) ? OperatorType.LIKE
					: FilterParserHelper.getOperator(field);
			String attributeName = FilterParserHelper.getAttributeName(field, alias);

			FilterAttributeModel attributeModel = new FilterAttributeModel(attributeName, operatorType);
			model.getPredicateAttributes().put(field, attributeModel);
		}
	}

	/**
	 * Processa os atributos do filtro do tipo Boolean.
	 * 
	 * @param alias
	 */
	private void processBooleanTypeAttribute(Field field, String alias) {

	}

	/**
	 * Processa os atributos do filtro do tipo Date.
	 * 
	 * @param alias
	 */
	private void processDateTypeAttribute(Field field, String alias) {

	}

	/**
	 * @param alias
	 * 
	 */
	private void processLongTypeAttribute(Field field, String alias) {

	}

	/**
	 * 
	 * @param field
	 * @param alias
	 */
	private void processIntegerTypeAttribute(Field field, String alias) {

	}

	/**
	 * 
	 * @param field
	 * @param alias
	 */
	private void processBigDecimalTypeAttribute(Field field, String alias) {

	}

	/**
	 * Processa os atributos do filtro do tipo List e Set.
	 * 
	 * @param alias
	 */
	private void processListTypeAttribute(Field field, String alias) {

	}

	/**
	 * Processa os atributos do filtro do tipo Map.
	 * 
	 * @param alias
	 */
	private void processMapTypeAttribute(Field field, String alias) {

	}

}
