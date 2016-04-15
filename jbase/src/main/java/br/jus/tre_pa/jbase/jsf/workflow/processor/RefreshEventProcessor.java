package br.jus.tre_pa.jbase.jsf.workflow.processor;

import javax.inject.Inject;

import br.jus.tre_pa.jbase.jsf.workflow.annotation.EventPattern;
import br.jus.tre_pa.jbase.jsf.workflow.base.EventProcessor;
import br.jus.tre_pa.jbase.jsf.workflow.context.EventContext;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

@EventPattern("refresh")
public class RefreshEventProcessor extends EventProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	public void process(EventContext context) {
		service.update(context.getFormId(), context.getBodyId());
	}

}
