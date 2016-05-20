package br.jus.tre_pa.jbase.filter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.jus.tre_pa.jbase.filter.enums.JunctionOperatorType;

/**
 * 
 * @author jcruz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FilterAttributesMap {

	/**
	 * 
	 * @return
	 */
	FilterAttribute[] value();

	/**
	 * 
	 * @return
	 */
	JunctionOperatorType junction() default JunctionOperatorType.OR;

}
