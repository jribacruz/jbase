package br.jus.tre_pa.jbase.jsf.validation.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.jus.tre_pa.jbase.jsf.validation.annotation.JSFValidationHandler;
import br.jus.tre_pa.jbase.jsf.validation.context.BusinessValidatorContext;
import br.jus.tre_pa.jbase.jsf.validation.exception.BusinessValidationException;
import br.jus.tre_pa.jbase.jsf.validation.model.BusinessValidationErrorItem;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

@Interceptor
@JSFValidationHandler
public class JSFValidationHandlerInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1222146161745214626L;

	@Inject
	private BusinessValidatorContext validatorContext;

	@Inject
	private MessageContext messageContext;

	@Inject
	private UIService uiservice;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = null;
		try {
			ret = ic.proceed();
		} catch (BusinessValidationException e) {
			for (BusinessValidationErrorItem item : validatorContext.getErrors()) {
				messageContext.add(item.getMessage());
			}
			uiservice.showError();
		} catch (Exception e) {
			messageContext.add(e.getMessage());
			uiservice.showError();
			e.printStackTrace();
		}
		return ret;
	}

}
