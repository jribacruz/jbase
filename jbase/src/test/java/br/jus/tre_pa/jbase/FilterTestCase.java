package br.jus.tre_pa.jbase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.jus.tre_pa.jbase.filter.internal.JPQLBuilder;

@RunWith(JUnit4.class)
public class FilterTestCase {

	@Test
	public void test1() {
		FooFilter fooFilter = new FooFilter();
		JPQLBuilder builder = new JPQLBuilder();
		String jpql = builder.build(fooFilter);
		System.out.println(jpql);
	}
}
