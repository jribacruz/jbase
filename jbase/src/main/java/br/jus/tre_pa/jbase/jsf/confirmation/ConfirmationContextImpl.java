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

	private boolean yesOptionConfirmation;

	private String message;

	@Override
	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public void setParamenters(Object[] params) {
		this.params = params;
	}

	@Override
	public void invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.method.invoke(target, params);
	}

	@Override
	public boolean isYesOptionConfirmation() {
		return this.yesOptionConfirmation;
	}

	@Override
	public String yesOptionConfitmation() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("Invocando Método: Inicio");
		this.yesOptionConfirmation = true;
		invoke();
		System.out.println("Invocando Método: Fim");
		return null;
	}

	@Override
	public void clear() {
		this.yesOptionConfirmation = false;
		this.method = null;
		this.target = null;
		this.params = null;
	}

	@Override
	public void setMethod(Method method) {
		this.method = method;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
