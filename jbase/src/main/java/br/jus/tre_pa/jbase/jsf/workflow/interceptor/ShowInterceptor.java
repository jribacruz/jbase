package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Show;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@Show
public class ShowInterceptor extends AbstractWorkflowInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Override
	protected void invokeOnSuccess(InvocationContext ic) {
		processShow(ic);
	}

	private String getForClass(InvocationContext ic) {
		Class<?> forClass = ic.getMethod().getAnnotation(Show.class).forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processShow(InvocationContext ic) {
		String forClassName = Strings.camelCaseToSymbolSeparated(getForClass(ic).replaceAll("MB", ""), "_");
		String wvar = String.format("%s_wvar", forClassName);
		service.show(wvar);
	}

}
