package br.jus.tre_pa.jbase.filter.internal;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

public class FilterAttributeModel {

	private String name;

	private OperatorType operatorType;

	public FilterAttributeModel() {
		super();
	}

	public FilterAttributeModel(String name, OperatorType operatorType) {
		super();
		this.name = name;
		this.operatorType = operatorType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

}
