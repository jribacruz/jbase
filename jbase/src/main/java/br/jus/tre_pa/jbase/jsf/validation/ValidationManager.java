package br.jus.tre_pa.jbase.jsf.validation;

import java.io.Serializable;

/**
 * 
 * @author jcruz
 *
 */
public interface ValidationManager extends Serializable {

	/**
	 * 
	 * @param bean
	 */
	void validate(Object... beans);

	void validate(String validatorGroup, Object... beans);

}
