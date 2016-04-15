package br.jus.tre_pa.jbase.jsf.workflow.implementation;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.context.EventContext;

public class EventContextImpl implements EventContext {

	private Object eventBean;

	private String methodName;

	private String managedBean;

	public EventContextImpl(Object eventBean, String methodName, String managedBean) {
		super();
		this.eventBean = eventBean;
		this.methodName = methodName;
		this.managedBean = managedBean;
	}

	public Object getEventBean() {
		if (eventBean == null) {
			this.eventBean = this;
		}
		return this.eventBean;
	}

	public String getActionName() {
		return this.methodName;
	}

	public String getActionNameAsKey() {
		return Strings.camelCaseToSymbolSeparated(this.methodName, ".");
	}

	public String getManagedBean() {
		return this.managedBean;
	}

	public String getManagedBeanAsKey() {
		return Strings.camelCaseToSymbolSeparated(this.managedBean.replaceAll("MB", ""), ".");
	}

	public String getManagedBeanAsId() {
		return Strings.camelCaseToSymbolSeparated(this.managedBean.replaceAll("MB", ""), "_");
	}

	public String getFormId() {
		return String.format("%s_form_id", getManagedBeanAsId());
	}

	public String getBodyId() {
		return String.format("%s_body_id", getManagedBeanAsId());
	}

	public String getFooterId() {
		return String.format("%s_footer_id", getManagedBeanAsId());
	}

	public String getHeaderId() {
		return String.format("%s_header_id", getManagedBeanAsId());
	}

	public String getWvar() {
		return String.format("%s_wvar", getManagedBeanAsId());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nEventContext [\n");
		sb.append(String.format("  Event Bean Class: %s,\n", getEventBean().getClass()));
		sb.append(String.format("  Action: %s,\n", getActionName()));
		sb.append(String.format("  Action as Key: %s,\n", getActionNameAsKey()));
		sb.append(String.format("  ManagedBean: %s,\n", getManagedBean()));
		sb.append(String.format("  ManagedBean as Id: %s,\n", getManagedBeanAsId()));
		sb.append(String.format("  ManagedBean as Key: %s,\n", getManagedBeanAsKey()));
		sb.append("]\n");
		return sb.toString();
	}

}
