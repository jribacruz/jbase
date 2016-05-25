package br.jus.tre_pa.jbase.jsf.workflow.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateRegion;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateRegions;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;
import br.jus.tre_pa.jbase.jsf.workflow.utils.InvocationContextUtil;

@Interceptor
@UpdateRegions
public class UpdateRegionsInterceptor extends AbstractWorkflowInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = ic.proceed();
		if (!isValidationFailed()) {
			UpdateRegions regions = ic.getMethod().getAnnotation(UpdateRegions.class);
			for (UpdateRegion updateRegion : regions.value()) {
				processUpdateRegion(ic, updateRegion);
			}
		}
		return ret;
	}

	private String getForClass(InvocationContext ic, UpdateRegion updateRegion) {
		Class<?> forClass = updateRegion.forClass();
		if (forClass == Void.class) {
			return InvocationContextUtil.getManagedBean(ic);
		}
		return forClass.getSimpleName();
	}

	private String[] getIds(UpdateRegion updateRegion) {
		return updateRegion.id();
	}

	private void processUpdateRegion(InvocationContext ic, UpdateRegion updateRegion) {
		String forClassId = Strings.camelCaseToSymbolSeparated(getForClass(ic, updateRegion).replaceAll("MB", ""), "_");
		for (String id : getIds(updateRegion)) {
			String componentId = String.format("%s_form_id:%s", forClassId, id);
			service.update(componentId);
		}
	}

}
