package br.jus.tre_pa.jbase.jsf.workflow.implementation;

import javax.enterprise.util.AnnotationLiteral;

import br.jus.tre_pa.jbase.jsf.workflow.annotation.On;

@SuppressWarnings("all")
public class OnQualifier extends AnnotationLiteral<On> implements On {

	private String eventName;

	public OnQualifier(String eventName) {
		super();
		this.eventName = eventName;
	}

	public String value() {
		return this.eventName;
	}

}
