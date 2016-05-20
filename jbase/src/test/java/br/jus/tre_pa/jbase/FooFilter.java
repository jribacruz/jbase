package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;

@Filter(alias = "foo", entityClass = Foo.class)
public class FooFilter implements Filterable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4319905310598685185L;

}
