package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.Filterable;
import br.jus.tre_pa.jbase.filter.annotation.Filter;

@Filter(alias = "foo", projection = { "foo.name", "foo.desc" })
public class FooFilterProjection implements Filterable<Foo> {

}
