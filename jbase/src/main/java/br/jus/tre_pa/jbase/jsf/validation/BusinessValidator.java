package br.jus.tre_pa.jbase.jsf.validation;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.jus.tre_pa.jbase.jsf.validation.exception.BusinessValidationException;

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
	

	/**
	 * 
	 * @param value
	 * @param messageOnFailure
	 */
	protected void failIsAlpha(String value, String messageOnFailure){
		if (StringUtils.isNotEmpty(value) && StringUtils.isNumeric(value)){
			fail(messageOnFailure);
		}
	}
	
	/**
	 * 
	 * @param value
	 * @param messageOnFailure
	 */
	protected void failIsValidDate(String value, String messageOnFailure){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		
		try {
			formatter.parse(value);
		} catch (ParseException e) {
			throw new BusinessValidationException(messageOnFailure);
		}
		
		
	}

}
