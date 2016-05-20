package br.jus.tre_pa.jbase.groovy.filter.fragment

/**
 * Representa um atributo da entidade.
 * 
 * @author jcruz
 *
 */
class EntityAttribute {

	/**
	 * Nome do atributo da entidade.
	 */
	String name

	/**
	 * Tipo do atributo de entidade.
	 */
	Class<?> type

	/**
	 * Valor do atributo da entidade.
	 */
	Object value



	EntityAttribute() {
		super()
	}

	EntityAttribute(String name, Class<?> type, Object value) {
		super()
		this.name = name
		this.type = type
		this.value = value
	}
}
