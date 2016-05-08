package br.jus.tre_pa.jbase;

import java.io.Serializable;

import br.jus.tre_pa.jbase.filter.annotation.FilterBean;

@FilterBean(alias = "foo", entity = FooEntity.class)
public class FooFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
