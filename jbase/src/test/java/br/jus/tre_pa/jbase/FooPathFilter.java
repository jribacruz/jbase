package br.jus.tre_pa.jbase;

import java.io.Serializable;

import br.jus.tre_pa.jbase.filter.annotation.FilterBean;
import br.jus.tre_pa.jbase.filter.annotation.FilterPath;

@FilterBean(alias = "foo", entity = FilterBean.class, paths = { @FilterPath(path = "foo.bars", alias = "bar"),
		@FilterPath(path = "bar.cars", alias = "car") })
public class FooPathFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
