package br.jus.tre_pa.jbase.jsf.workflow.base;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UIActionPattern;
import br.jus.tre_pa.jbase.jsf.workflow.context.EventContext;

public abstract class UIActionProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void process(EventContext context);

	protected String format(String format, Object... args) {
		return String.format(format, args);
	}

	protected String group(String input, int i) {
		Matcher matcher = Pattern.compile(this.getClass().getAnnotation(UIActionPattern.class).value()).matcher(input);
		if (matcher.matches()) {
			return Strings.camelCaseToSymbolSeparated(matcher.group(i), "_");
		}
		return null;
	}

}
