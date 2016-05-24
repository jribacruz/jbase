package br.jus.tre_pa.jbase;

import java.util.Date;

import br.jus.tre_pa.jbase.groovy.filter.fragment.EntityAttribute;
import br.jus.tre_pa.jbase.groovy.filter.fragment.SelectStatement;

public class JPQLSelectBuilderApp {

	public static void main(String[] args) {

		EntityAttribute attr1 = new EntityAttribute("foo.name", String.class, "jcruz");

		EntityAttribute attr2 = new EntityAttribute("foo.habilitado", Boolean.class, true);

		EntityAttribute attr3 = new EntityAttribute("foo.dtCriacao", Date.class, new Date());

		EntityAttribute attr4 = new EntityAttribute("foo.dtExclusao", Date.class, new Date());

		SelectStatement select = new SelectStatement(Foo.class, "foo");
		select.getAttributes().add(attr1);
		select.getAttributes().add(attr2);

		System.out.println(select.buildJPQLFragment());
	}

}
