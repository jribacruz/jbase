package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateRegion;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateRegion(id = "")
public class UpdateRegionInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = ic.proceed();
		if (!FacesContext.getCurrentInstance().isValidationFailed()) {
			processUpdateRegion(ic);
		}
		return ret;
	}

	private String getForClass(InvocationContext ic) {
		Class<?> forClass = ic.getMethod().getAnnotation(UpdateRegion.class).forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private String getId(InvocationContext ic) {
		return ic.getMethod().getAnnotation(UpdateRegion.class).id();
	}

	private void processUpdateRegion(InvocationContext ic) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic).replaceAll("MB", ""), "_");
		String id = String.format("%s_form_id:%s", forClassId, getId(ic));
		service.update(id);
	}

}
