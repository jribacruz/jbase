package br.jus.tre_pa.jbase.jsf.validation.context;

import java.io.Serializable;
import java.util.List;

import br.jus.tre_pa.jbase.jsf.validation.model.ValidationErrorItem;

/**
 * 
 * @author jcruz
 *
 */
public interface ValidationContext extends Serializable {

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

	/**
	 * 
	 * @return
	 */
	List<ValidationErrorItem> getErrors();
}
