package br.jus.tre_pa.jbase.jsf.validation.impl;

import java.util.Iterator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.validation.AbstractValidator;
import br.jus.tre_pa.jbase.jsf.validation.ValidationManager;
import br.jus.tre_pa.jbase.jsf.validation.annotation.ValidatorGroup;
import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;
import br.jus.tre_pa.jbase.jsf.validation.exception.BusinessValidationException;

/**
 * 
 * @author jcruz
 *
 */
public class ValidationManagerImpl implements ValidationManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Obtem todas as classes que extendem {@link AbstractValidator}
	 */
	@Inject
	@Any
	private Instance<AbstractValidator<?>> validators;

	@Inject
	private Logger log;

	/**
	 * 
	 */
	@Inject
	private Multimap<Class<?>, AbstractValidator<?>> validatorsMap;

	/**
	 * 
	 */
	@Inject
	private ValidationContext validatorContext;

	/**
	 * Chama todos os validadores para uma determinada entidade. Em caso de
	 * falha (exception BusinessValidationException lançada) as mensagens de
	 * erro são acumuladas.
	 * 
	 */
	@Override
	public void validate(Object... beans) {
		int failCounter = 0;
		int okCounter = 0;
		StringBuilder sb = new StringBuilder();
		validatorContext.clear();
		for (Object object : beans) {
			sb.append(String.format("\n\nValidators => [%s]\n", object.getClass().getSimpleName()));
			validation(object, failCounter, okCounter, sb, null);
		}
		checkFail(sb);
	}

	@Override
	public void validate(String validatorGroup, Object... beans) {
		int failCounter = 0;
		int okCounter = 0;
		StringBuilder sb = new StringBuilder();
		validatorContext.clear();
		for (Object object : beans) {
			sb.append(String.format("\n\nValidators => [%s]\n", object.getClass().getSimpleName()));
			validation(object, failCounter, okCounter, sb, validatorGroup);
		}
		checkFail(sb);
	}

	/**
	 * @param sb
	 * @throws BusinessValidationException
	 */
	private void checkFail(StringBuilder sb) throws BusinessValidationException {
		/*
		 * Em caso de algum validador falhe uma exception
		 * BusinessValidationException é lançada impedindo a chamada do método
		 * de negócio (insert, update etc...)
		 */
		if (validatorContext.isValidationFailed()) {
			log.debug(sb.toString());
			throw new BusinessValidationException();
		}
		log.debug(sb.toString());
	}

	/**
	 * @param bean
	 * @param failCounter
	 * @param okCounter
	 * @param sb
	 * @throws BusinessValidationException
	 */
	@SuppressWarnings("unchecked")
	private <T> void validation(T bean, int failCounter, int okCounter, StringBuilder sb, String alias) throws BusinessValidationException {
		if (bean != null) {
			Class<?> beanClass = bean.getClass();
			Iterator<AbstractValidator<?>> iterValidatorsForBeanClass = validatorsMap.get(beanClass).iterator();
			/*
			 * Itera por todos o validadores (BusinessValidator) de uma
			 * determinada entidade T.
			 */
			while (iterValidatorsForBeanClass.hasNext()) {
				AbstractValidator<T> validator = (AbstractValidator<T>) iterValidatorsForBeanClass.next();
				if (alias == null) {
					if (!validator.getClass().isAnnotationPresent(ValidatorGroup.class)) {
						executeValidation(validator, bean, failCounter, okCounter, sb, alias);
					}
				} else if (validator.getClass().isAnnotationPresent(ValidatorGroup.class)) {
					if (validator.getClass().getAnnotation(ValidatorGroup.class).value().toLowerCase().trim()
							.equals(alias.toLowerCase().trim())) {
						executeValidation(validator, bean, failCounter, okCounter, sb, alias);
					}
				}
				sb.append(String.format("Total => [OK=%d], [FAIL=%d] \n", okCounter, failCounter));
			}
		}
	}

	public <T> void executeValidation(AbstractValidator<T> validator, T bean, int failCounter, int okCounter, StringBuilder sb,
			String alias) {
		try {
			validator.validate(bean);
			sb.append(String.format("\tValidator %s => [OK]\n", validator.getClass().getSimpleName()));
			okCounter++;
		} catch (BusinessValidationException e) {
			validatorContext.addMessage(e.getMessage());
			/*
			 * Torna o estado de validação como falho.
			 */
			validatorContext.validationFailed();
			sb.append(String.format("\tValidator %s => [FAIL]\n", validator.getClass().getSimpleName()));
			failCounter++;
		}
	}

	/**
	 * Cria um Multimapa com a classe da entidade como chave.
	 * 
	 * @return
	 */
	@ApplicationScoped
	@Produces
	public Multimap<Class<?>, AbstractValidator<?>> create() {
		Multimap<Class<?>, AbstractValidator<?>> map = ArrayListMultimap.create();
		Iterator<AbstractValidator<?>> iter = validators.iterator();
		while (iter.hasNext()) {
			AbstractValidator<?> validator = iter.next();
			Class<?> validatorBeanClass = Reflections.getGenericTypeArgument(validator.getClass(), 0);
			if (!validatorBeanClass.isAnnotationPresent(Ignore.class)) {
				map.put(validatorBeanClass, validator);
			}
		}
		return map;
	}

}
