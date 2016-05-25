package br.jus.tre_pa.jbase.jsf.validation.impl;

import java.util.Iterator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import br.gov.frameworkdemoiselle.annotation.Ignore;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.validation.AbstractValidator;
import br.jus.tre_pa.jbase.jsf.validation.ValidationManager;
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
	 * Chama todos os validadores para uma determinada entidade. Em caso de falha (exception BusinessValidationException lançada) as
	 * mensagens de erro são acumuladas.
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void validate(T bean) {
		validatorContext.clear();
		if (bean != null) {
			Class<?> beanClass = bean.getClass();
			Iterator<AbstractValidator<?>> iterValidatorsForBeanClass = validatorsMap.get(beanClass).iterator();
			/*
			 * Itera por todos o validadores (BusinessValidator) de uma determinada entidade T.
			 */
			while (iterValidatorsForBeanClass.hasNext()) {
				AbstractValidator<T> validator = (AbstractValidator<T>) iterValidatorsForBeanClass.next();
				/*
				 * Verifica se o validator não está ignorado.
				 */
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
