package br.jus.tre_pa.jbase.filter.fragment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class EntityAttribute {

	private String name;

	private Class<?> type;

	private Object value;

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

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
