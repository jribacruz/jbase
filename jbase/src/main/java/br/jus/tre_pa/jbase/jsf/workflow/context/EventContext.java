package br.jus.tre_pa.jbase.jsf.workflow.context;

public interface EventContext {

	Object getEventBean();

	String getActionName();

	String getActionNameAsKey();

	String getManagedBean();

	String getManagedBeanAsKey();

	String getManagedBeanAsId();

	String getFormId();

	String getBodyId();

	String getHeaderId();

	String getFooterId();

	String getWvar();
}
