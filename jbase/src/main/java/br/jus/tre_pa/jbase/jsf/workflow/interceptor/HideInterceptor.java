package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@Hide
public class HideInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Inject
	private ValidationContext validatorContext;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = ic.proceed();
		if (isValidationFailed()) {
			processHide(ic);
		}
		return ret;
	}

	private boolean isValidationFailed() {
		return !FacesContext.getCurrentInstance().isValidationFailed() && !validatorContext.isValidationFailed();
	}

	private void processHide(InvocationContext ic) {
		String forClassName = Strings.camelCaseToSymbolSeparated(InvocationContextUtil.getForClass(ic, Hide.class).replaceAll("MB", ""),
				"_");
		String wvar = String.format("%s_wvar", forClassName);
		service.hide(wvar);
	}

}
