package br.jus.tre_pa.jbase.filter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.tre_pa.jbase.filter.enums.JoinType;

/**
 * 
 * @author jcruz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface FilterPath {
	/**
	 * 
	 * @return
	 */
	String path();

	/**
	 * 
	 * @return
	 */
	JoinType join() default JoinType.LEFT;
}
