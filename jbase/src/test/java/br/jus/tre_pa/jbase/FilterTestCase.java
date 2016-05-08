package br.jus.tre_pa.jbase;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.jus.tre_pa.jbase.filter.internal.FilterBuilder;

@RunWith(JUnit4.class)
public class FilterTestCase {
	
	@After
	public void init() {
		System.out.println("====================================\n");
	}

	@Test
	public void filterBuildTest() {
		FilterBuilder builder = new FilterBuilder();
		String sql = builder.buildSQL(new FooFilter());

		System.out.println(sql);
	}
	
	@Test
	public void filterBuildProjectionTest() {
		FilterBuilder builder = new FilterBuilder();
		String sql = builder.buildSQL(new FooProjectionFilter());

		System.out.println(sql);
	}
	
	@Test
	public void filterBuildPathTest() {
		FilterBuilder builder = new FilterBuilder();
		String sql = builder.buildSQL(new FooPathFilter());

		System.out.println(sql);
	}
}
