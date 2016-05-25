package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBody;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateBody
public class UpdateBodyInterceptor extends AbstractWorkflowInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Override
	protected void invokeOnSuccess(InvocationContext ic) {
		processUpdateBody(ic);
	}

	private String getForClass(InvocationContext ic) {
		Class<?> forClass = ic.getMethod().getAnnotation(UpdateBody.class).forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processUpdateBody(InvocationContext ic) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic).replaceAll("MB", ""), "_");
		String id = String.format("%s_form_id:%s_body_id", forClassId, forClassId);
		service.update(id);
	}

}
