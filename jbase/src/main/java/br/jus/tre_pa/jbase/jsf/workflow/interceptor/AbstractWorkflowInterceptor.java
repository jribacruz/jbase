package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.jus.tre_pa.jbase.jsf.confirmation.ConfirmAction;
import br.jus.tre_pa.jbase.jsf.confirmation.ConfirmationContext;
import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;
import br.jus.tre_pa.jbase.jsf.validation.exception.BusinessValidationException;
import br.jus.tre_pa.jbase.jsf.validation.model.ValidationErrorItem;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

/**
 * 
 * @author jcruz
 *
 */
public abstract class AbstractWorkflowInterceptor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1290051769551399882L;

	@Inject
	private ValidationContext validationContext;

	@Inject
	private MessageContext messageContext;

	@Inject
	private ConfirmationContext confirmationContext;

	@Inject
	private UIService uiservice;

	/**
	 * 
	 * @param ic
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = null;
		try {
			validationContext.clear();
			ret = ic.proceed();
			if (!isValidationFailed()) {
				if (ic.getMethod().isAnnotationPresent(ConfirmAction.class)) {
					if (confirmationContext.isYesOption()) {
						invokeOnSuccess(ic);
						confirmationContext.clear();
						return ret;
					}
					return ret;
				}
				invokeOnSuccess(ic);
				return ret;
			}
		} catch (BusinessValidationException e) {
			for (ValidationErrorItem item : validationContext.getErrors()) {
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

	/**
	 * Método que deverá ser implementado pelos interceptors. Só é invocado quando o status de validação(FacesContext e ValidationContext)
	 * for false.
	 * 
	 * @param ic
	 */
	protected abstract void invokeOnSuccess(InvocationContext ic);

	/**
	 * 
	 * 
	 * @return
	 */
	protected boolean isValidationFailed() {
		return FacesContext.getCurrentInstance().isValidationFailed() || validationContext.isValidationFailed();
	}

}
