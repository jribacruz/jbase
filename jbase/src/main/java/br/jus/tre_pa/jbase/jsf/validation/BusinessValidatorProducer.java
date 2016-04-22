package br.jus.tre_pa.jbase.jsf.validation;

import java.io.Serializable;
import java.util.Iterator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import br.gov.frameworkdemoiselle.util.Reflections;

/**
 * 
 * @author jcruz
 *
 */
public class BusinessValidatorProducer<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Inject
	@Any
	private Instance<BusinessValidator<T>> validators;

	/**
	 * 
	 * @return
	 */
	@ApplicationScoped
	@Produces
	public Multimap<Class<T>, BusinessValidator<T>> create() {
		Multimap<Class<T>, BusinessValidator<T>> map = ArrayListMultimap.create();
		Iterator<BusinessValidator<T>> iter = validators.iterator();
		while (iter.hasNext()) {
			BusinessValidator<T> validator = iter.next();
			Class<T> validatorBeanClass = Reflections.getGenericTypeArgument(validator.getClass(), 0);
			map.put(validatorBeanClass, validator);
		}
		return map;
	}
}
