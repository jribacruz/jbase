package br.jus.tre_pa.jbase;

import java.util.Date;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;
import br.jus.tre_pa.jbase.filter.fragment.EntityAttribute;
import br.jus.tre_pa.jbase.filter.fragment.WhereBinaryPredicate;
import br.jus.tre_pa.jbase.filter.fragment.WhereDatePredicateParam;
import br.jus.tre_pa.jbase.filter.fragment.WherePredicateExpression;
import br.jus.tre_pa.jbase.filter.fragment.WherePredicateGroupedExpression;
import br.jus.tre_pa.jbase.filter.fragment.WhereStatement;
import br.jus.tre_pa.jbase.filter.fragment.WhereStringPredicateParam;
import br.jus.tre_pa.jbase.filter.fragment.WhereUnaryPredicate;

public class JPQLWhereBuilderApp {

	public static void main(String[] args) {

		WhereStatement where = new WhereStatement();

		EntityAttribute attr1 = new EntityAttribute("name", String.class, "jcruz");

		EntityAttribute attr2 = new EntityAttribute("habilitado", Boolean.class, true);

		EntityAttribute attr3 = new EntityAttribute("dtCriacao", Date.class, new Date());

		EntityAttribute attr4 = new EntityAttribute("dtExclusao", Date.class, new Date());

		/**
		 * EXP1
		 */
		WherePredicateExpression exp1 = new WherePredicateExpression();
		// Predicado 1
		WhereUnaryPredicate pred1 = new WhereUnaryPredicate(attr2, OperatorType.IS_TRUE);
		exp1.getPredicates().add(pred1);

		// Predicado 2
		WhereBinaryPredicate pred2 = new WhereBinaryPredicate(attr1, OperatorType.LIKE);
		exp1.getPredicates().add(pred2);
		WhereStringPredicateParam pred2Param = new WhereStringPredicateParam(1, pred2);
		pred2.setParam(pred2Param);

		/**
		 * EXP2
		 */
		WherePredicateGroupedExpression exp2 = new WherePredicateGroupedExpression();
		// Predicado 3
		WhereBinaryPredicate pred3 = new WhereBinaryPredicate(attr3, OperatorType.GREATER_THAN_OR_EQUAL_TO);
		exp2.getPredicates().add(pred3);
		WhereDatePredicateParam pred3Param = new WhereDatePredicateParam(2, pred3);
		pred3.setParam(pred3Param);

		// Predicado 4
		WhereBinaryPredicate pred4 = new WhereBinaryPredicate(attr4, OperatorType.LESS_THAN);
		exp2.getPredicates().add(pred4);
		WhereDatePredicateParam pred4Param = new WhereDatePredicateParam(3, pred4);
		pred4.setParam(pred4Param);

		where.getPredicateExpressions().add(exp1);
		where.getPredicateExpressions().add(exp2);

		System.out.println(where.buildJPQLFragment());
	}

}