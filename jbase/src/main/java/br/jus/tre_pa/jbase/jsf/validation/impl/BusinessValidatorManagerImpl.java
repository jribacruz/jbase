package br.jus.tre_pa.jbase.jsf.validation.impl;

import java.util.Iterator;

import javax.inject.Inject;

import com.google.common.collect.Multimap;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.jus.tre_pa.jbase.jsf.validation.BusinessValidatorManager;
import br.jus.tre_pa.jbase.jsf.validation.context.BusinessValidatorContext;
import br.jus.tre_pa.jbase.jsf.validation.exception.BusinessValidationException;
import br.jus.tre_pa.jbase.jsf.validation.BusinessValidator;

/**
 * 
 * @author jcruz
 *
 */
public class BusinessValidatorManagerImpl<T> implements BusinessValidatorManager<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Inject
	private Multimap<Class<T>, BusinessValidator<T>> validatorsMap;

	/**
	 * 
	 */
	@Inject
	private BusinessValidatorContext validatorContext;

	/**
	 * Chama todos os validadores para uma determinada entidade. Em caso de falha (exception BusinessValidationException lançada) as
	 * mensagens de erro são acumuladas.
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(T bean) {
		validatorContext.clear();
		if (bean != null) {
			Class<T> beanClass = (Class<T>) bean.getClass();
			Iterator<BusinessValidator<T>> iterValidatorsForBeanClass = validatorsMap.get(beanClass).iterator();
			/*
			 * Itera por todos o validadores (BusinessValidator) de uma determinada entidade T.
			 */
			while (iterValidatorsForBeanClass.hasNext()) {
				BusinessValidator<T> validator = iterValidatorsForBeanClass.next();
				/*
				 * Verifica se o validator não está ignorado.
				 */
				if (!isIgnoredValidator(validator)) {
					try {
						validator.validate(bean);
					} catch (BusinessValidationException e) {
						validatorContext.addMessage(e.getMessage());
						/*
						 * Torna o estado de validação como falho.
						 */
						validatorContext.validationFailed();
					}
				}
			}
			/*
			 * Em caso de algum validador falhe uma exception BusinessValidationException é lançada impedindo a chamada do método de negócio
			 * (insert, update etc...)
			 */
			if (validatorContext.isValidationFailed()) {
				throw new BusinessValidationException();
			}
		}
	}

	/**
	 * Checa a presença da annotation @Ignore na classe de Validação de Nogócio (BusinessValidator).
	 * 
	 * @param validator
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private boolean isIgnoredValidator(BusinessValidator validator) {
		return validator.getClass().isAnnotationPresent(Ignore.class);
	}

}
