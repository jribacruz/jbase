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
@UIActionPattern("close")
public class CloseUIActionProcessor extends UIActionProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UIService service;

	/**
	 * 
	 */
	@Override
	public void process(EventContext context) {
		service.hide(context.getWvar());
	}

}
