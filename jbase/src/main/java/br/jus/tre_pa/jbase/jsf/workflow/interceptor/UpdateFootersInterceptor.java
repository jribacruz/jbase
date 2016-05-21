package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateFooter;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateFooters;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateFooters
public class UpdateFootersInterceptor implements Serializable {

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
			UpdateFooters footers = ic.getMethod().getAnnotation(UpdateFooters.class);
			for (UpdateFooter footer : footers.value()) {
				processUpdateFooter(ic, footer);
			}
		}
		return ret;
	}

	private String getForClass(InvocationContext ic, UpdateFooter updateFooter) {
		Class<?> forClass = updateFooter.forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processUpdateFooter(InvocationContext ic, UpdateFooter updateFooter) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic, updateFooter).replaceAll("MB", ""), "_");
		String id = String.format("%s_form_id:%s_footer_id", forClassId, forClassId);
		service.update(id);
	}

}
