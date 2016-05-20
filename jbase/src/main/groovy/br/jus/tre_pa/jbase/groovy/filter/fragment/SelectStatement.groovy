package br.jus.tre_pa.jbase.groovy.filter.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Representa uma seleção (SELECT) da JPQL.
 * 
 * @author jcruz
 *
 */
class SelectStatement extends AbstractJPQLFragment {

	/**
	 * Lista de atributos da projeção (Usados para a montagem da declaração de SELECT com construtor de classe).
	 */
	List<EntityAttribute> attributes = new ArrayList<EntityAttribute>();

	/**
	 * Alias da clausula FROM.
	 */
	String alias;

	/**
	 * Entidade da clausula FROM.
	 */
	Class<?> entityClass;



	SelectStatement() {
		super();
	}

	SelectStatement(Class<?> entityClass, String alias) {
		super();
		this.entityClass = entityClass;
		this.alias = alias;
	}

	/**
	 * Monta a clausula select.
	 */
	@Override
	String buildJPQLFragment() {
		if (this.attributes.isEmpty()) {
			return "select ${alias} from ${entityClass.simpleName} ${alias}";
		}
		def attributesConstructor = attributes.collect {attr -> attr.name}.join(',')
		return "select new ${entityClass.simpleName}(${attributesConstructor}) from ${entityClass.simpleName} ${alias} "
	}

	/**
	 * 
	 * Monta clausula select de contagem.
	 * 
	 * @return
	 */
	String buildCountJPQLFragment() {
		return "select count(${alias}) from ${entityClass.simpleName} ${alias} "
	}
}
