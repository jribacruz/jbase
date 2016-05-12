package br.jus.tre_pa.jbase.filter.fragment;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import br.jus.tre_pa.jbase.filter.enums.JPQLStatementType;

/**
 * Representa uma declaração JPQL completa.
 * 
 * @author jcruz
 *
 */
public class JPQLStatement extends AbstractJPQLFragment {

	/**
	 * 
	 */
	private Map<JPQLStatementType, String> statements = new HashMap<JPQLStatementType, String>();

	private SelectStatement selectStatement;

	private PathStatement pathStatement;

	private WhereStatement whereStatement;

	private OrderByStatement orderByStatement;

	public JPQLStatement(SelectStatement selectStatement) {
		super();
		this.selectStatement = selectStatement;
	}

	@Override
	public String buildJPQLFragment() {
		getFragments().add(selectStatement.buildJPQLFragment());
		if (pathStatement != null) {
			getFragments().add(pathStatement.buildJPQLFragment());
		}
		if (this.whereStatement != null) {
			getFragments().add(whereStatement.buildJPQLFragment());
		}
		if (this.orderByStatement != null) {
			getFragments().add(orderByStatement.buildJPQLFragment());
		}
		return joinStatements();
	}

	private String joinStatements() {
		return StringUtils.join(getFragments(), "");
	}

	public PathStatement getPathStatement() {
		return pathStatement;
	}

	public void setPathStatement(PathStatement pathStatement) {
		this.pathStatement = pathStatement;
	}

	public WhereStatement getWhereStatement() {
		return whereStatement;
	}

	public void setWhereStatement(WhereStatement whereStatement) {
		this.whereStatement = whereStatement;
	}

	public OrderByStatement getOrderByStatement() {
		return orderByStatement;
	}

	public void setOrderByStatement(OrderByStatement orderByStatement) {
		this.orderByStatement = orderByStatement;
	}

	public SelectStatement getSelectStatement() {
		return selectStatement;
	}

}
