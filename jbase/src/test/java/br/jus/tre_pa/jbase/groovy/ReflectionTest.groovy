package br.jus.tre_pa.jbase.groovy

import br.jus.tre_pa.jbase.Foo

class ReflectionTest {

	static main(args) {
		Foo foo  = new Foo();
		foo.class.declaredFields.each { field ->
			println field.name
			field.annotations.each {
				println it
			}
		}
	}
}
