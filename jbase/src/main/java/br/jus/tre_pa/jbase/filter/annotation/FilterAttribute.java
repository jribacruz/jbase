package br.jus.tre_pa.jbase.filter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.tre_pa.jbase.filter.enums.OperatorType;

/**
 * 
 * @author jcruz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
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
	OperatorType operator() default OperatorType.EQUAL;

	/**
	 * 
	 * @return
	 */
	Class<?> type() default Void.class;

}
