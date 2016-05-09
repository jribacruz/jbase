package br.jus.tre_pa.jbase;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import br.jus.tre_pa.jbase.filter.OperatorType;
import br.jus.tre_pa.jbase.filter.annotation.FilterAttribute;
import br.jus.tre_pa.jbase.filter.annotation.FilterMap;
import br.jus.tre_pa.jbase.filter.annotation.FilterBean;

@FilterBean(alias = "foo", entity = FooEntity.class, projection = { "name", "desc" }, orderBy = "nome")
public class FooWhereFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FilterAttribute
	private String name;
	
	@FilterMap({
		@FilterAttribute(name="dtInicio", type=Date.class, operator=OperatorType.GREATER_THAN),
		@FilterAttribute(name="dtFim", type=Date.class, operator=OperatorType.LESS_THAN)
	})
	private Map<String, String> maps;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
