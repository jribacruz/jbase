package br.jus.tre_pa.jbase.jsf.workflow.implementation;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.jus.tre_pa.jbase.jsf.workflow.base.UIName;
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

	private RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	@Override
	public void update(String id) {
		log.debug("[update] id: {}", id);
		getRequestContext().update(id);
	}

	@Override
	public void update(String formId, String id) {
		id = String.format("%s:%s", formId, id);
		log.debug("[update] id: {}", id);
		getRequestContext().update(id);
	}

	@Override
	public void execute(String wvar) {
		log.debug("[execute] wvar : {}", wvar);
		getRequestContext().execute(wvar);
	}

	@Override
	public void addCallbackParam(String name, String value) {
		log.debug("[addCallbackParam] Name: {}, Value: {}", name, value);
		getRequestContext().addCallbackParam(name, value);
	}

	@Override
	public void scrollTo(String id) {
		log.debug("[scrollTo] id: {}", id);
		getRequestContext().scrollTo(id);
	}

	@Override
	public void show(String wvar) {
		wvar = String.format("PF('%s').show()", wvar);
		log.debug("[show] wvar : {}", wvar);
		getRequestContext().execute(wvar);
	}

	@Override
	public void hide(String wvar) {
		wvar = String.format("PF('%s').hide()", wvar);
		log.debug("[hide] wvar : {}", wvar);
		getRequestContext().execute(wvar);
	}

	@Override
	public void fireEvent(String eventKey, Object bean) {
		log.debug("[fireEvent] eventKey: {}, bean: {}", eventKey, bean.getClass().getCanonicalName());
		beanManager.fireEvent(bean, new OnQualifier(eventKey));
	}

	@Override
	public void showError() {
		this.update("error_form_id:error_body_id");
		this.show("error_wvar");
	}

	@Override
	public void showGrowl() {
		this.update("growl_id");
	}

	@Override
	public void updateBody(Class<?> forClass) {
		UIName name = new UIName(forClass);
		update(name.getFormId(), name.getBodyId());
	}

	@Override
	public void updateFooter(Class<?> forClass) {
		UIName name = new UIName(forClass);
		update(name.getFormId(), name.getFooterId());
	}

	@Override
	public void updateHeader(Class<?> forClass) {
		UIName name = new UIName(forClass);
		update(name.getFormId(), name.getHeaderId());
	}

	@Override
	public void updateRegion(Class<?> forClass, String id) {
		update(new UIName(forClass).getFormId(), id);
	}

	@Override
	public void show(Class<?> forClass) {
		show(new UIName(forClass).getWvar());
	}

	@Override
	public void hide(Class<?> forClass) {
		hide(new UIName(forClass).getWvar());
	}

	@Override
	public void hightlightEffect(Class<?> beanClass, String id) {
		hightlightEffect(new UIName(beanClass).getFormId(), id);
	}

	@Override
	public void hightlightEffect(String formId, String id) {
		hightlightEffect(String.format("%s:%s", formId, id));
	}

	@Override
	public void hightlightEffect(String id) {
		execute(String.format("jbase.highlight('%s')", id));
	}

}
