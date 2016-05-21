package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBody;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBodys;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateBodys
public class UpdateBodysInterceptor implements Serializable {

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
			UpdateBodys bodys = ic.getMethod().getAnnotation(UpdateBodys.class);
			for(UpdateBody body: bodys.value()) {
				processUpdateBody(ic, body);
			}
		}
		return ret;
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
