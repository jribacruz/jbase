package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateRegion;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

@Interceptor
@UpdateRegion(id = "")
public class UpdateRegionInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Inject
	private MessageContext messageContext;

	private Logger log = LoggerFactory.getLogger(UpdateRegionInterceptor.class);

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		log.debug("==>[invoke] Target: {}, Method: {}, Params: {}",
				new Object[] { ic.getTarget().getClass().getSuperclass().getSimpleName(), ic.getMethod().getName(), ic.getParameters() });
		Object ret = null;
		try {
			ret = ic.proceed();
			/*
			 * Os processors só serão executados se o estado do FacesContext não estiver falho.
			 */
			if (!FacesContext.getCurrentInstance().isValidationFailed()) {
				processUpdateRegion(ic);
			}
			return ret;
		} catch (Exception e) {
			messageContext.add(e.getMessage());
			service.showError();
			e.printStackTrace();
		}
		return ret;
	}

	private String getManagedBean(InvocationContext ic) {
		if (ic.getTarget().getClass().getSuperclass().isAnnotationPresent(Named.class)) {
			return ic.getTarget().getClass().getSuperclass().getAnnotation(Named.class).value();
		}
		return ic.getTarget().getClass().getSuperclass().getSimpleName();
	}

	private String getForClass(InvocationContext ic) {
		Class<?> forClass = ic.getMethod().getAnnotation(UpdateRegion.class).forClass();
		if (forClass == Void.class) {
			return getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private String[] getIds(InvocationContext ic) {
		return ic.getMethod().getAnnotation(UpdateRegion.class).id();
	}

	private void processUpdateRegion(InvocationContext ic) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic).replaceAll("MB", ""), "_");
		for (String id : getIds(ic)) {
			String componentId = String.format("%s_form_id:%s", forClassId, id);
			service.update(componentId);
		}
	}

}
