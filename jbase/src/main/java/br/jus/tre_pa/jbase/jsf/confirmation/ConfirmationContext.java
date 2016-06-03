package br.jus.tre_pa.jbase.jsf.confirmation;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface ConfirmationContext extends Serializable {

	void setMessage(String message);

	String getMessage();

	void setMethod(Method method);

	void setTarget(Object target);

	void setParamenters(Object[] params);

	void invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	boolean isYesOptionConfirmation();

	String yesOptionConfitmation() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	void clear();

}
