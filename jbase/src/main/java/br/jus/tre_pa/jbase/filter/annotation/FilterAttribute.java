package br.jus.tre_pa.jbase.filter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author jcruz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FilterAttribute {

	/**
	 * 
	 * Nome do atributo da entidade.
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * 
	 * @return
	 */
	String pathAlias() default "";

}
