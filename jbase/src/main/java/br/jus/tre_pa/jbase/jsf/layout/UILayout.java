package br.jus.tre_pa.jbase.jsf.layout;

import java.io.Serializable;

public interface UILayout extends Serializable {

	public UILayoutMap getRendered();

	public void setRendered(String key, boolean rendered);

}
