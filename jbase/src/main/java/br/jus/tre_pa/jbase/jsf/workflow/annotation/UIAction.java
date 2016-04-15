package br.jus.tre_pa.jbase.jsf.workflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface UIAction {
	@Nonbinding
	String name() default "";

	@Nonbinding
	String[] update() default "";

	@Nonbinding
	String[] show() default "";

	@Nonbinding
	String[] hide() default "";

	@Nonbinding
	String[] execute() default "";

	@Nonbinding
	String[] scrollTo() default "";

}
