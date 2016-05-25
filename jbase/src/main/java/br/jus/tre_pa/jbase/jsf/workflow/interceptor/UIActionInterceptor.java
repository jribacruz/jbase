package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.frameworkdemoiselle.util.Beans;
import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UIAction;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UIActionPattern;
import br.jus.tre_pa.jbase.jsf.workflow.base.EventTargetBean;
import br.jus.tre_pa.jbase.jsf.workflow.base.UIActionProcessor;
import br.jus.tre_pa.jbase.jsf.workflow.context.EventContext;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.implementation.EventContextImpl;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UIAction
public class UIActionInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Injeta todos os processadores do sistema
	 */
	@Inject
	@Any
	private Instance<UIActionProcessor> actionProcessors;

	@SuppressWarnings("rawtypes")
	@Inject
	@Any
	private Instance<EventTargetBean> eventTargetBeans;

	@Inject
	private UIService service;

	@Inject
	private ValidationContext businessValidatorContext;

	private Map<String, String> context;

	private Logger log = LoggerFactory.getLogger(UIActionInterceptor.class);

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = ic.proceed();
		/*
		 * Os processors só serão executados se o estado do FacesContext não
		 * estiver falho.
		 */
		if (!FacesContext.getCurrentInstance().isValidationFailed() && !businessValidatorContext.isValidationFailed()) {
			EventContext eventContext = createEventContext(ic);
			log.debug(eventContext.toString());
			Iterator<UIActionProcessor> it = actionProcessors.iterator();
			while (it.hasNext()) {
				UIActionProcessor processor = it.next();
				if (executeProcessor(eventContext, processor)) {
					break;
				}
			}
			processUpdate(ic);
			processShow(ic);
			processHide(ic);
			processExecute(ic);
			processScrollTo(ic);
			fireEvent(eventContext);
		}
		return ret;
	}

	private boolean executeProcessor(EventContext eventContext, UIActionProcessor processor) {
		if (processor.getClass().isAnnotationPresent(UIActionPattern.class)) {
			Pattern pattern = Pattern.compile(processor.getClass().getAnnotation(UIActionPattern.class).value());
			/*
			 * Verifica o padrão entre o nome do método interceptado e o
			 * EventPattern do anotado na classe do processador.
			 */
			if (pattern.matcher(eventContext.getActionName()).matches()) {
				processor.process(eventContext);
				log.debug("[invoke] Processor {} executado.", processor.getClass().getSimpleName());
				return true;
			}
		}
		return false;
	}

	private void fireEvent(EventContext eventContext) {
		service.fireEvent(String.format("%s.%s", eventContext.getManagedBeanAsKey(), eventContext.getActionNameAsKey()),
				eventContext.getEventBean());
	}

	private EventContext createEventContext(InvocationContext ic) {
		String actionName = getActionName(ic);
		Object eventBean = getEventBean(ic);
		String managedBean = InvocationContextUtil.getManagedBean(ic);
		EventContext eventContext = new EventContextImpl(eventBean, actionName, managedBean);
		this.context = new HashMap<String, String>();
		this.context.put("formId", eventContext.getFormId());
		this.context.put("bodyId", eventContext.getBodyId());
		this.context.put("headerId", eventContext.getHeaderId());
		this.context.put("footerId", eventContext.getFooterId());
		this.context.put("wvar", eventContext.getWvar());
		return eventContext;
	}

	private String getActionName(InvocationContext ic) {
		String value = ic.getMethod().getAnnotation(UIAction.class).name();
		return StringUtils.isNotBlank(value) ? value : ic.getMethod().getName();
	}

	@SuppressWarnings("rawtypes")
	private Object getEventBean(InvocationContext ic) {
		log.debug("==>[getEventBean] Declaring Class {}", ic.getMethod().getDeclaringClass());
		Class<?> declaringClass = ic.getMethod().getDeclaringClass();
		if (EventTargetBean.class.isAssignableFrom(declaringClass)) {
			EventTargetBean ev = (EventTargetBean) Beans.getReference(ic.getMethod().getDeclaringClass());
			return ev.getEventTargetBean();
		}
		return null;
	}

	private void processUpdate(InvocationContext ic) {
		String[] ids = ic.getMethod().getAnnotation(UIAction.class).update();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				StrSubstitutor substitutor = new StrSubstitutor(context);
				service.update(substitutor.replace(ids[i]));
			}
		}
	}

	private void processShow(InvocationContext ic) {
		String[] wvar = ic.getMethod().getAnnotation(UIAction.class).show();
		for (int i = 0; i < wvar.length; i++) {
			if (StringUtils.isNotBlank(wvar[i])) {
				StrSubstitutor substitutor = new StrSubstitutor(context);
				service.show(substitutor.replace(wvar[i]));
			}
		}
	}

	private void processHide(InvocationContext ic) {
		String[] wvar = ic.getMethod().getAnnotation(UIAction.class).hide();
		for (int i = 0; i < wvar.length; i++) {
			if (StringUtils.isNotBlank(wvar[i])) {
				StrSubstitutor substitutor = new StrSubstitutor(context);
				service.hide(substitutor.replace(wvar[i]));
			}
		}
	}

	private void processExecute(InvocationContext ic) {
		String[] wvar = ic.getMethod().getAnnotation(UIAction.class).execute();
		for (int i = 0; i < wvar.length; i++) {
			if (StringUtils.isNotBlank(wvar[i])) {
				StrSubstitutor substitutor = new StrSubstitutor(context);
				service.execute(substitutor.replace(wvar[i]));
			}
		}
	}

	private void processScrollTo(InvocationContext ic) {
		String[] ids = ic.getMethod().getAnnotation(UIAction.class).scrollTo();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				StrSubstitutor substitutor = new StrSubstitutor(context);
				service.update(substitutor.replace(ids[i]));
			}
		}
	}

}
