package br.jus.tre_pa.jbase.jsf.workflow.utils;

import javax.inject.Named;
import javax.interceptor.InvocationContext;

import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;

/**
 * 
 * @author jcruz
 *
 */
public class InvocationContextUtil {

	/**
	 * 
	 * @param ic
	 * @return
	 */
	public static String getManagedBean(InvocationContext ic) {
		if (ic.getTarget().getClass().getSuperclass().isAnnotationPresent(Named.class)) {
			return ic.getTarget().getClass().getSuperclass().getAnnotation(Named.class).value();
		}
		return ic.getTarget().getClass().getSuperclass().getSimpleName();
	}

	/**
	 * 
	 * @param ic
	 * @param hideClass
	 * @return
	 */
	public static String getForClass(InvocationContext ic, Class<Hide> hideClass) {
		Class<?> forClass = ic.getMethod().getAnnotation(hideClass).forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

}
