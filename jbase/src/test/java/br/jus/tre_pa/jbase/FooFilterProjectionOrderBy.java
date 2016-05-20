package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;

@Filter(alias = "foo", projection = { "foo.name", "foo.desc" }, entityClass = Foo.class)
public class FooFilterProjectionOrderBy implements Filterable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6488175213276987469L;

}
