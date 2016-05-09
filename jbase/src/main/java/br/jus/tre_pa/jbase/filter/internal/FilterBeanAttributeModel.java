package br.jus.tre_pa.jbase.filter.internal;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.jus.tre_pa.jbase.filter.OperatorType;
import br.jus.tre_pa.jbase.filter.annotation.FilterAttribute;

public class FilterBeanAttributeModel {

	/**
	 * 
	 */
	private Field field;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String pathAlias;

	/**
	 * 
	 */
	private OperatorType operatorType;

	/**
	 * 
	 */
	private Object value;

	public FilterBeanAttributeModel(Field field) {
		super();
		this.field = field;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		if (this.name == null) {
			this.name = this.field.getAnnotation(FilterAttribute.class).name();
		}
		return name;
	}

	/**
	 * Retorna o alias do atributo do filtro.
	 * 
	 * @return
	 */
	public String getPathAlias() {
		if (this.pathAlias == null) {
			this.pathAlias = this.field.getAnnotation(FilterAttribute.class).pathAlias();
		}
		return pathAlias;
	}

	/**
	 * Retorna o operador lógico padrão da atributo do filtro.
	 * 
	 * @return
	 */
	public OperatorType getOperatorType() {
		if (this.operatorType == null) {
			this.operatorType = getDefaultOperatorType();
			this.operatorType = this.field.getAnnotation(FilterAttribute.class).operator();
		}
		return operatorType;
	}

	/**
	 * Retorna o operador lógico padrão dado o tipo do attributo.
	 * 
	 * @return
	 */
	private OperatorType getDefaultOperatorType() {
		Class<?> fieldTypeClass = field.getType();
		Map<Class<?>, OperatorType> map = new HashMap<Class<?>, OperatorType>();
		map.put(String.class, OperatorType.LIKE);
		map.put(Boolean.class, OperatorType.IS_TRUE);
		map.put(Long.class, OperatorType.EQUAL);
		map.put(Integer.class, OperatorType.EQUAL);
		map.put(Short.class, OperatorType.EQUAL);
		map.put(Date.class, OperatorType.EQUAL);
		map.put(BigDecimal.class, OperatorType.EQUAL);
		map.put(BigInteger.class, OperatorType.EQUAL);
		map.put(Set.class, OperatorType.IN);
		map.put(List.class, OperatorType.IN);
		map.put(Collection.class, OperatorType.IN);
		return map.get(fieldTypeClass) != null ? map.get(fieldTypeClass) : OperatorType.EQUAL;
	}

}
