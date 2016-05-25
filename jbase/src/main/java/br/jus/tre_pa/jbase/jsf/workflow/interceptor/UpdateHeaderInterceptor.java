package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateHeader;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateHeader
public class UpdateHeaderInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Inject
	private ValidationContext businessValidatorContext;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = ic.proceed();
		if (!FacesContext.getCurrentInstance().isValidationFailed() && !businessValidatorContext.isValidationFailed()) {
			processUpdateHeader(ic);
		}
		return ret;
	}

	private String getForClass(InvocationContext ic) {
		Class<?> forClass = ic.getMethod().getAnnotation(UpdateHeader.class).forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processUpdateHeader(InvocationContext ic) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic).replaceAll("MB", ""), "_");
		String id = String.format("%s_form_id:%s_header_id", forClassId, forClassId);
		service.update(id);
	}

}
