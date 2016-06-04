package br.jus.tre_pa.jbase.jsf.confirmation;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

@Interceptor
@ConfirmAction(value = "")
public class ConfirmInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3984397641251708862L;

	@Inject
	private ConfirmationContext confirmationContext;

	@Inject
	private UIService uiservice;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = null;
		if (confirmationContext.isYesOption()) {
			return ic.proceed();
		}
		confirmationContext.setMessageConfirmation(ic.getMethod().getAnnotation(ConfirmAction.class).value());
		confirmationContext.setMethod(ic.getMethod());
		confirmationContext.setTarget(ic.getTarget());
		confirmationContext.setParameters(ic.getParameters());
		uiservice.update("confirm_form_id", "confirm_body_id");
		uiservice.show("confirm_wvar");
		return ret;
	}

}
