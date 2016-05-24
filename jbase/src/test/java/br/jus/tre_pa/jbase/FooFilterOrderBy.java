package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;

@Filter(alias = "foo", orderBy = { "foo.name ASC" }, entityClass = Foo.class)
public class FooFilterOrderBy implements Filterable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6370377689893978297L;

}
