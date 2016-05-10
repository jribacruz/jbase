package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;

@Filter(alias = "foo", orderBy = { "foo.name ASC" })
public class FooFilterOrderBy implements Filterable<Foo> {

}
