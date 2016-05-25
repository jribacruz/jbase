package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@Hide
public class HideInterceptor extends AbstractWorkflowInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Override
	protected void invokeOnSuccess(InvocationContext ic) {
		processHide(ic);
	}

	private void processHide(InvocationContext ic) {
		String forClassName = Strings.camelCaseToSymbolSeparated(InvocationContextUtil.getForClass(ic, Hide.class).replaceAll("MB", ""),
				"_");
		String wvar = String.format("%s_wvar", forClassName);
		service.hide(wvar);
	}

}
