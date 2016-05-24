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
@Target(ElementType.TYPE)
public @interface Filter {
	
	
	Class<?> entityClass();
	/**
	 * 
	 * @return
	 */
	String[] projection() default "";

	/**
	 * 
	 * @return
	 */
	FilterPath[] paths() default {};

	/**
	 * 
	 * @return
	 */
	String[] orderBy() default "";

	/**
	 * 
	 * @return
	 */
	String alias() default "";
}
