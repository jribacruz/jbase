package br.jus.tre_pa.jbase.filter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.tre_pa.jbase.filter.JoinType;

/**
 * 
 * @author jcruz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FilterMap {

	/**
	 * 
	 * @return
	 */
	FilterAttribute[] value();

	/**
	 * 
	 * @return
	 */
	JoinType join() default JoinType.OR;
}
