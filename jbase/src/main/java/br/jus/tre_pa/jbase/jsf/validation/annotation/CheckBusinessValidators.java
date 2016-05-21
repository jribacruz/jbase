package br.jus.tre_pa.jbase.jsf.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * 
 * @author jcruz
 *
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface CheckBusinessValidators {
	/**
	 * 
	 * @return
	 */
	@Nonbinding
	String group() default "";
}