package br.jus.tre_pa.jbase.jsf.workflow.base;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.util.Strings;

/**
 * 
 * @author jcruz
 *
 */
public class UIName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1680573025057453460L;

	private Class<?> beanClass;

	/**
	 * 
	 */
	private String formId;

	/**
	 * 
	 */
	private String bodyId;

	/**
	 * 
	 */
	private String headerId;

	/**
	 * 
	 */
	private String footerId;

	/**
	 * 
	 */
	private String wvar;

	/**
	 * 
	 */
	private String id;

	/**
	 * 
	 */
	private String key;

	public UIName(Class<?> beanClass) {
		super();
		this.beanClass = beanClass;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> getBeanClass() {
		return beanClass;
	}

	/**
	 * 
	 * @return
	 */
	public String getHeaderId() {
		if (this.headerId == null) {
			this.headerId = String.format("%s_header_id", getAsId());
		}
		return headerId;
	}

	/**
	 * 
	 * @return
	 */
	public String getFooterId() {
		if (this.footerId == null) {
			this.footerId = String.format("%s_footer_id", getAsId());
		}
		return footerId;
	}

	/**
	 * 
	 * @return
	 */
	public String getFormId() {
		if (this.formId == null) {
			this.formId = String.format("%s_form_id", getAsId());
		}
		return formId;
	}

	/**
	 * 
	 * @return
	 */
	public String getBodyId() {
		if (this.bodyId == null) {
			this.bodyId = String.format("%s_body_id", getAsId());
		}
		return bodyId;
	}

	/**
	 * 
	 * @return
	 */
	public String getWvar() {
		if (this.wvar == null) {
			this.wvar = String.format("%s_wvar", getAsId());
		}
		return wvar;
	}

	/**
	 * Retorna o nome da classe como ID.
	 * 
	 * @return
	 */
	public String getAsId() {
		if (this.id == null) {
			this.id = Strings.camelCaseToSymbolSeparated(this.beanClass.getSimpleName().replaceAll("MB", ""), "_");
		}
		return id;
	}

	/**
	 * Retorna o nome da classe como KEY.
	 * 
	 * @return
	 */
	public String getAsKey() {
		if (this.key == null) {
			this.key = Strings.camelCaseToSymbolSeparated(this.beanClass.getSimpleName().replaceAll("MB", ""), ".");
		}
		return key;
	}

}
