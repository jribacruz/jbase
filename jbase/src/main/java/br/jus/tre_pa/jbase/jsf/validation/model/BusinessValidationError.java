package br.jus.tre_pa.jbase.jsf.validation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessValidationError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7936788915834548657L;

	@XmlElementWrapper(name = "errors")
	@XmlElement(name = "error")
	private List<BusinessValidationErrorItem> errorList;

	public BusinessValidationError() {
		super();
	}

	public List<BusinessValidationErrorItem> getErrorList() {
		if (this.errorList == null) {
			this.errorList = new ArrayList<BusinessValidationErrorItem>();
		}
		return errorList;
	}

	public void setErrorList(List<BusinessValidationErrorItem> errors) {
		this.errorList = errors;
	}

}
