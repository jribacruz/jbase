package br.jus.tre_pa.jbase.jsf.dev;

import java.io.File;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * Expõe informações importantes para os desenvolvedores.
 * 
 * @author jcruz
 *
 */
@SessionScoped
@Named
public class DevInfoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String warName;

	/**
	 * Retorna o nome da app war.
	 * 
	 * @return
	 */
	public String getWarName() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.warName = new File(req.getServletContext().getRealPath("/")).getName().replace(".war", "");
		return this.warName;
	}

}
