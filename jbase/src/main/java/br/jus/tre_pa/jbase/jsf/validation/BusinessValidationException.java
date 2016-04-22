package br.jus.tre_pa.jbase.jsf.validation;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

/**
 * 
 * @author jcruz
 *
 */
@ApplicationException(rollback = true, severity = SeverityType.ERROR)
public class BusinessValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessValidationException(String message) {
		super(message);
	}

}
