package br.jus.tre_pa.jbase.jsf.validation.impl;

import javax.enterprise.context.SessionScoped;

import br.jus.tre_pa.jbase.jsf.validation.context.BusinessValidatorContext;
import br.jus.tre_pa.jbase.jsf.validation.model.BusinessValidationError;
import br.jus.tre_pa.jbase.jsf.validation.model.BusinessValidationErrorItem;

/**
 * 
 * @author jcruz
 *
 */
@SessionScoped
public class BusinessValidatorContextImpl implements BusinessValidatorContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8279373289524249805L;

	private boolean validationFailed = false;

	/**
	 * 
	 */
	private BusinessValidationError errors = new BusinessValidationError();

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

	@Override
	public void addMessage(String message) {
		this.errors.getErrorList().add(new BusinessValidationErrorItem(message));
	}

}
