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
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Show;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

@Interceptor
@Show
public class ShowInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Inject
	private MessageContext messageContext;

	private Logger log = LoggerFactory.getLogger(ShowInterceptor.class);

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		log.debug("==>[invoke] Target: {}, Method: {}, Params: {}",
				new Object[] { ic.getTarget().getClass().getSuperclass().getSimpleName(), ic.getMethod().getName(), ic.getParameters() });
		Object ret = null;
		try {
			ret = ic.proceed();
			if (!FacesContext.getCurrentInstance().isValidationFailed()) {
				processShow(ic);
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
		Class<?> forClass = ic.getMethod().getAnnotation(Show.class).forClass();
		if (forClass == Void.class) {
			return getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processShow(InvocationContext ic) {
		String forClassName = Strings.camelCaseToSymbolSeparated(getForClass(ic).replaceAll("MB", ""), "_");
		String wvar = String.format("%s_wvar", forClassName);
		service.show(wvar);
	}

}
