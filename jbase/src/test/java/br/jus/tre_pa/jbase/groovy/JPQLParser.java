package br.jus.tre_pa.jbase.groovy;

import br.jus.tre_pa.jbase.FooFilterProjection;
import br.jus.tre_pa.jbase.filter.FilterParser;
import br.jus.tre_pa.jbase.groovy.filter.FilterParserImpl;
import br.jus.tre_pa.jbase.groovy.filter.fragment.JPQLStatement;

public class JPQLParser {

	public static void main(String[] args) {
		FilterParser parser = new FilterParserImpl();
		
		FooFilterProjection f1 = new FooFilterProjection();
		
		JPQLStatement statement = parser.parse(f1);
		
		System.out.println(statement.buildJPQLFragment());
	}

}
