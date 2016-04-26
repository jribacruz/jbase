package br.jus.tre_pa.jbase.jsf.validation;

import java.io.Serializable;

/**
 * 
 * @author jcruz
 *
 */
public interface BusinessValidatorManager<T> extends Serializable {

	/**
	 * 
	 * @param bean
	 */
	void validate(T bean);

}
