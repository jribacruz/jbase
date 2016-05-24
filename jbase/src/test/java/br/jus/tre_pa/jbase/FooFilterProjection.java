package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;

@Filter(alias = "foo", projection = { "foo.name", "foo.desc" }, orderBy = { "foo.name ASC" }, entityClass = Foo.class)
public class FooFilterProjection implements Filterable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4289751763295030442L;

}
