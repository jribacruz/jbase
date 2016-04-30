package br.jus.tre_pa.jbase.jsf.workflow.context;

import java.io.Serializable;

/**
 * 
 * @author jcruz
 *
 */
public interface UIService extends Serializable {

	/**
	 * 
	 * @param id
	 */
	void update(String id);

	/**
	 * 
	 * @param formId
	 * @param id
	 */
	void update(String formId, String id);

	/**
	 * 
	 * @param forClass
	 */
	void updateBody(Class<?> forClass);

	/**
	 * 
	 * @param forClass
	 */
	void updateFooter(Class<?> forClass);

	/**
	 * 
	 * @param forClass
	 */
	void updateHeader(Class<?> forClass);

	/**
	 * 
	 * @param forClass
	 * @param id
	 */
	void updateRegion(Class<?> forClass, String id);

	/**
	 * 
	 * @param wvar
	 */
	void execute(String wvar);

	/**
	 * 
	 * @param name
	 * @param value
	 */
	void addCallbackParam(String name, String value);

	/**
	 * 
	 * @param id
	 */
	void scrollTo(String id);

	/**
	 * 
	 * @param wvar
	 */
	void show(String wvar);

	/**
	 * 
	 * @param forClass
	 */
	void show(Class<?> forClass);

	/**
	 * 
	 * @param wvar
	 */
	void hide(String wvar);

	/**
	 * 
	 * @param forClass
	 */
	void hide(Class<?> forClass);

	/**
	 * 
	 * @param id
	 */
	void hightlightEffect(String id);

	/**
	 * 
	 * @param beanClass
	 * @param id
	 */
	void hightlightEffect(Class<?> beanClass, String id);

	/**
	 * 
	 * @param formId
	 * @param id
	 */
	void hightlightEffect(String formId, String id);

	/**
	 * 
	 * @param eventKey
	 * @param bean
	 */
	void fireEvent(String eventKey, Object bean);

	/**
	 * 
	 */
	void showError();

	/**
	 * 
	 */
	void showGrowl();

}
