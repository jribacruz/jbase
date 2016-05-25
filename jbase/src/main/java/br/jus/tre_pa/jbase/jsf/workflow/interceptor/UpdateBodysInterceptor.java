package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBody;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBodys;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateBodys
public class UpdateBodysInterceptor extends AbstractWorkflowInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Override
	protected void invokeOnSuccess(InvocationContext ic) {
		UpdateBodys bodys = ic.getMethod().getAnnotation(UpdateBodys.class);
		for (UpdateBody body : bodys.value()) {
			processUpdateBody(ic, body);
		}
	}

	private String getForClass(InvocationContext ic, UpdateBody updateBody) {
		Class<?> forClass = updateBody.forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processUpdateBody(InvocationContext ic, UpdateBody updateBody) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic, updateBody).replaceAll("MB", ""), "_");
		String id = String.format("%s_form_id:%s_body_id", forClassId, forClassId);
		service.update(id);
	}

}
