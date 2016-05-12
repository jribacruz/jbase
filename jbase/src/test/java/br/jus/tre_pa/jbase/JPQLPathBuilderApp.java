package br.jus.tre_pa.jbase;

import br.jus.tre_pa.jbase.filter.enums.JoinType;
import br.jus.tre_pa.jbase.filter.fragment.PathExpression;
import br.jus.tre_pa.jbase.filter.fragment.PathStatement;

public class JPQLPathBuilderApp {

	public static void main(String[] args) {
		PathStatement pathStatement = new PathStatement();

		PathExpression pExpression1 = new PathExpression("foo.bars", "bar", JoinType.LEFT);
		
		PathExpression pExp2 = new PathExpression("bar.cars", "cars", JoinType.LEFT);

		pathStatement.getPaths().add(pExpression1);
		pathStatement.getPaths().add(pExp2);

		System.err.println(pathStatement.buildJPQLFragment());
	}

}
