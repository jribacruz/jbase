package br.jus.tre_pa.jbase.jsf.validation.impl;

import javax.enterprise.context.SessionScoped;

import br.jus.tre_pa.jbase.jsf.validation.BusinessValidatorContext;

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
	}

}
