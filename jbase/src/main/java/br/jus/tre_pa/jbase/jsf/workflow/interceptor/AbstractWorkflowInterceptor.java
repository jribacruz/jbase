package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;

import br.jus.tre_pa.jbase.jsf.validation.context.ValidationContext;

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

	public abstract Object invoke(InvocationContext ic) throws Exception;

	/**
	 * 
	 * 
	 * @return
	 */
	protected boolean isValidationFailed() {
		return FacesContext.getCurrentInstance().isValidationFailed() || validationContext.isValidationFailed();
	}

}
