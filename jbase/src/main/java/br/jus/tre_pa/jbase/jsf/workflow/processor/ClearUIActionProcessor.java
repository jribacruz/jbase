package br.jus.tre_pa.jbase.jsf.workflow.processor;

import javax.inject.Inject;

import br.jus.tre_pa.jbase.jsf.workflow.annotation.UIActionPattern;
import br.jus.tre_pa.jbase.jsf.workflow.base.UIActionProcessor;
import br.jus.tre_pa.jbase.jsf.workflow.context.EventContext;
import br.jus.tre_pa.jbase.jsf.workflow.context.UIService;

/**
 * 
 * @author jcruz
 *
 */
@UIActionPattern("clear")
public class ClearUIActionProcessor extends UIActionProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	/**
	 * 
	 */
	public void process(EventContext context) {
		service.update(context.getFormId(), context.getBodyId());
	}

}
