package br.jus.tre_pa.jbase.filter.fragment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Representa um atributo da entidade.
 * 
 * @author jcruz
 *
 */
public class EntityAttribute {

	/**
	 * Nome do atributo da entidade.
	 */
	private String name;

	/**
	 * Tipo do atributo de entidade.
	 */
	private Class<?> type;

	/**
	 * Valor do atributo da entidade.
	 */
	private Object value;

	public EntityAttribute(String name, Class<?> type, Object value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getValueAsString() {
		return String.valueOf(value);
	}

	public Boolean getValueAsBoolean() {
		return (Boolean) value;
	}

	public Integer getValueAsInteger() {
		return (Integer) value;
	}

	public Long getValueAsLong() {
		return (Long) value;
	}

	public BigDecimal getValueAsBigDecimal() {
		return (BigDecimal) value;
	}

	public BigInteger getValueAsBigInteger() {
		return (BigInteger) value;
	}

	public Date getValueAsDate() {
		return (Date) value;
	}

	@SuppressWarnings("rawtypes")
	public List getValueAsList() {
		return (List) value;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

}
