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
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void validate(T bean) {
		validatorContext.clear();
		if (bean != null) {
			Class<T> beanClass = (Class<T>) bean.getClass();
			Iterator<BusinessValidator<T>> iterValidatorsForBeanClass = validatorsMap.get(beanClass).iterator();
			while (iterValidatorsForBeanClass.hasNext()) {
				BusinessValidator<T> validator = iterValidatorsForBeanClass.next();
				if (!isIgnoredValidator(validator)) {
					try {
						validator.validate(bean);
					} catch (BusinessValidationException e) {
						validatorContext.addMessage(e.getMessage());
						validatorContext.validationFailed();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param validator
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private boolean isIgnoredValidator(BusinessValidator validator) {
		return validator.getClass().isAnnotationPresent(Ignore.class);
	}

}
