package br.jus.tre_pa.jbase.jsf.validation;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * Classe que define um ponto de validação de negócio para a entidade T.
 * 
 * @author jcruz
 *
 * @param <T>
 *            Entidade que será validada.
 */
public abstract class BusinessValidator<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que executa a validação do bean T.
	 * 
	 * @param bean
	 *            Entidade a ser validada.
	 * @throws BusinessValidationException
	 *             Exception lançada em caso de falha na validação.
	 */
	public abstract void validate(T bean) throws BusinessValidationException;

	/**
	 * Lança a exception BusinessValidationException informando que a validação
	 * falhou.
	 * 
	 * @param message
	 */
	protected void fail(String message) {
		throw new BusinessValidationException(message);
	}

	/**
	 * 
	 * 
	 * @param value
	 * @return
	 */
	protected void failIsBlank(String value, String messageOnFailure) {
		if (StringUtils.isBlank(value)) {
			fail(messageOnFailure);
		}
	}

	/**
	 * 
	 * @param value
	 * @param messageOnFailure
	 */
	protected void failIsEqualZero(int value, String messageOnFailure) {
		if (value == 0) {
			fail(messageOnFailure);
		}
	}

}
