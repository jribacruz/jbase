package br.jus.tre_pa.jbase;

import java.io.Serializable;

import br.jus.tre_pa.jbase.filter.FilterOperatorType;
import br.jus.tre_pa.jbase.filter.annotation.FilterAttribute;
import br.jus.tre_pa.jbase.filter.annotation.FilterBean;
import br.jus.tre_pa.jbase.filter.annotation.FilterOperator;

@FilterBean(alias = "foo", entity = FooEntity.class, projection = { "name", "desc" }, orderBy = "nome")
public class FooWhereFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FilterAttribute
	@FilterOperator(FilterOperatorType.LIKE)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
