package br.jus.tre_pa.jbase.jsf.confirmation;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface ConfirmationContext extends Serializable {

	void setMessageConfirmation(String message);

	String getMessageConfirmation();

	void setMethod(Method method);

	void setTarget(Object target);

	void setParameters(Object[] params);

	void invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	boolean isYesOption();

	String yesOption() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	void clear();

}
