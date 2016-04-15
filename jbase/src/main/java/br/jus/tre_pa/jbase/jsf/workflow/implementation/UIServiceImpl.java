package br.jus.tre_pa.jbase.jsf.workflow.implementation;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

@RequestScoped
public class UIServiceImpl implements UIService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private BeanManager beanManager;

	private Logger log = LoggerFactory.getLogger(UIServiceImpl.class);

	public void update(String id) {
		log.debug("[update] id: {}", id);
		getRequestContext().update(id);
	}

	public void update(String formId, String id) {
		id = String.format("%s:%s", formId, id);
		log.debug("[update] id: {}", id);
		getRequestContext().update(id);
	}

	public void update(String formId, String parentId, String id) {
		if (StringUtils.isNotBlank(parentId)) {
			id = String.format("%s:%s:%s", formId, parentId, id);
			log.debug("[update] id: {}", id);
			getRequestContext().update(id);
			return;
		}
		update(formId, id);
	}

	public void execute(String wvar) {
		log.debug("[execute] wvar : {}", wvar);
		getRequestContext().execute(wvar);
	}

	public void addCallbackParam(String name, String value) {
		log.debug("[addCallbackParam] Name: {}, Value: {}", name, value);
		getRequestContext().addCallbackParam(name, value);
	}

	public void scrollTo(String id) {
		log.debug("[scrollTo] id: {}", id);
		getRequestContext().scrollTo(id);
	}

	public void scrollTo(String formId, String id) {
		id = String.format("%s:%s", formId, id);
		log.debug("[scrollTo] id : {}", id);
		getRequestContext().scrollTo(id);
	}

	public void scrollTo(String formId, String parentId, String id) {
		if (StringUtils.isNotBlank(parentId)) {
			id = String.format("%s:%s:%s", formId, parentId, id);
			log.debug("[scrollTo] id: {}", id);
			getRequestContext().scrollTo(id);
			return;
		}
		scrollTo(formId, id);
	}

	public void show(String wvar) {
		wvar = String.format("PF('%s').show()", wvar);
		log.debug("[show] wvar : {}", wvar);
		getRequestContext().execute(wvar);
	}

	public void hide(String wvar) {
		wvar = String.format("PF('%s').hide()", wvar);
		log.debug("[hide] wvar : {}", wvar);
		getRequestContext().execute(wvar);
	}

	public void hightlightEffect(String id) {
	}

	public void hightlightEffect(String formId, String id) {
	}

	public void hightlightEffect(String formId, String parentId, String id) {
	}

	public void fireEvent(String eventKey, Object bean) {
		log.debug("[fireEvent] eventKey: {}, bean: {}", eventKey, bean.getClass().getCanonicalName());
		beanManager.fireEvent(bean, new OnQualifier(eventKey));
	}

	private RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	public void showError() {
		this.update("error_form_id:error_body_id");
		this.show("error_wvar");
	}

	public void showGrowl() {
		this.update("growl_id");
	}

}
