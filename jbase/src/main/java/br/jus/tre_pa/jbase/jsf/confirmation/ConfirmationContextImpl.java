package br.jus.tre_pa.jbase.jsf.confirmation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("confirmationContext")
public class ConfirmationContextImpl implements ConfirmationContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3360081068464581454L;

	private Method method;

	private Object target;

	private Object[] params;

	private boolean yesOption;

	private String messageConfirmation;

	@Override
	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public void setParameters(Object[] params) {
		this.params = params;
	}

	@Override
	public void invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.method.invoke(target, params);
	}

	@Override
	public boolean isYesOption() {
		return this.yesOption;
	}

	@Override
	public String yesOption() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.yesOption = true;
		invoke();
		return null;
	}

	@Override
	public void clear() {
		this.yesOption = false;
		this.method = null;
		this.target = null;
		this.params = null;
	}

	@Override
	public void setMethod(Method method) {
		this.method = method;
	}

	@Override
	public void setMessageConfirmation(String message) {
		this.messageConfirmation = message;
	}

	@Override
	public String getMessageConfirmation() {
		return this.messageConfirmation;
	}

}
