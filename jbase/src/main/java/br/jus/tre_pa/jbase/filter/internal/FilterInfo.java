package br.jus.tre_pa.jbase.filter.internal;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;
import br.jus.tre_pa.jbase.filter.annotation.FilterAttribute;
import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * @author jcruz
 *
 */
public class FilterInfo {

	public static List<Field> getAttributesAsField(Object bean) {
		return new ArrayList<Field>();
	}

	public static String getAlias(Object bean) {
		return bean.getClass().getAnnotation(Filter.class).alias();
	}

	public static boolean isStringTypeField(Field field) {
		return field.getType() == String.class;
	}

	public static OperatorType getOperator(Field field) {
		return field.getAnnotation(FilterAttribute.class).operator();
	}

	public static boolean isDefaultOperator(Field field) {
		return getOperator(field).equals(OperatorType.DEFAULT);
	}

	public static String getAttributeName(Field field, String alias) {
		if (field.isAnnotationPresent(FilterAttribute.class)) {
			String attibuteName = field.getAnnotation(FilterAttribute.class).name();
			if (!Strings.isEmpty(attibuteName)) {
				if (!attibuteName.contains(".")) {
					return alias.concat(".").concat(attibuteName);
				}
			}
		}
		return alias.concat(".").concat(field.getName());
	}

	@SuppressWarnings("unchecked")
	public static <T, F extends Filterable<T>> Class<T> getEntityClass(F filter) {
		for (Type type : filter.getClass().getGenericInterfaces()) {
			ParameterizedType paramType = null;
			try {
				paramType = (ParameterizedType) type;
			} catch (ClassCastException cause) {
				cause.printStackTrace();
			}
			return (Class<T>) paramType.getActualTypeArguments()[0];
		}
		return null;
	}
}
