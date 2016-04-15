package br.jus.tre_pa.jbase.jsf.workflow.implementation;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.jus.tre_pa.jbase.jsf.layout.UILayout;
import br.jus.tre_pa.jbase.jsf.layout.UILayoutMap;

@SessionScoped
@Named("layout")
public class UILayoutImpl implements UILayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UILayoutMap map;

	public UILayoutImpl() {
		super();
		this.map = new UILayoutMap();
	}

	public UILayoutMap getRendered() {
		return map;
	}

	public void setRendered(String key, boolean rendered) {
		map.put(key, rendered);
	}

}
