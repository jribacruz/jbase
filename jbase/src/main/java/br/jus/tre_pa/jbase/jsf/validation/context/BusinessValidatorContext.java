package br.jus.tre_pa.jbase.jsf.validation.context;

import java.io.Serializable;

public interface BusinessValidatorContext extends Serializable {

	/**
	 * 
	 */
	void validationFailed();

	/**
	 * 
	 * @return
	 */
	boolean isValidationFailed();

	/**
	 * 
	 */
	void clear();

	/**
	 * 
	 * @param message
	 */
	void addMessage(String message);
}
