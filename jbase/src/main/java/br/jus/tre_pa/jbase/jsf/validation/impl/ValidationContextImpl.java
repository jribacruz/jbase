package br.jus.tre_pa.jbase.jsf.validation.impl;

import java.util.List;

import javax.enterprise.context.SessionScoped;

import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;
import br.jus.tre_pa.jbase.jsf.validation.model.ValidationError;
import br.jus.tre_pa.jbase.jsf.validation.model.ValidationErrorItem;

/**
 * 
 * @author jcruz
 *
 */
@SessionScoped
public class ValidationContextImpl implements ValidationContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8279373289524249805L;

	private boolean validationFailed = false;

	/**
	 * 
	 */
	private ValidationError errors = new ValidationError();

	/**
	 * 
	 */
	@Override
	public void validationFailed() {
		this.validationFailed = true;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public boolean isValidationFailed() {
		return this.validationFailed;
	}

	/**
	 * 
	 */
	@Override
	public void clear() {
		this.validationFailed = false;
		this.errors.getErrorList().clear();
	}

	/**
	 * 
	 */
	@Override
	public void addMessage(String message) {
		this.errors.getErrorList().add(new ValidationErrorItem(message));
	}

	/**
	 * 
	 */
	@Override
	public List<ValidationErrorItem> getErrors() {
		return this.errors.getErrorList();
	}

}
