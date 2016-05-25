package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateHeader;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateHeaders;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateHeaders
public class UpdateHeadersInterceptor extends AbstractWorkflowInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@Override
	protected void invokeOnSuccess(InvocationContext ic) {
		UpdateHeaders headers = ic.getMethod().getAnnotation(UpdateHeaders.class);
		for (UpdateHeader header : headers.value()) {
			processUpdateHeader(ic, header);
		}
	}

	private String getForClass(InvocationContext ic, UpdateHeader updateHeader) {
		Class<?> forClass = updateHeader.forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private void processUpdateHeader(InvocationContext ic, UpdateHeader updateHeader) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic, updateHeader).replaceAll("MB", ""), "_");
		String id = String.format("%s_form_id:%s_header_id", forClassId, forClassId);
		service.update(id);
	}

}
