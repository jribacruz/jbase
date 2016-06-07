package br.jus.tre_pa.jbase.jsf.template;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBody;

public abstract class AbstractUploadDialogPageBean<T, R extends Uploadable<T, R>> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T parentBean;

	private R selectedBean;

	private Class<T> beanClass;

	private Class<R> selectedBeanClass;

	/**
	 * implementação deve constar a anotation @UIAction
	 */
	public abstract String load(Long id);

	public abstract String load(T parentBean);

	/**
	 * implementação deve constar a anotation @Hide
	 */
	public abstract String update();

	protected abstract List<R> handleResultList();

	@Hide
	public void cancel() {

	}

	@UpdateBody
	public String delete(R doc) {
		handleResultList().remove(doc);
		return null;
	}

	@UpdateBody
	public String upload(FileUploadEvent fileUploaded) {
		R doc = getSelectedBean();
		doc = doc.prepareUpload(fileUploaded, parentBean);
		handleResultList().add(doc);
		return null;
	}

	public StreamedContent download(R doc) {
		InputStream stream = new ByteArrayInputStream(doc.getByteContent());
		return new DefaultStreamedContent(stream, doc.getTypeContent(), doc.getNameContent());
	}

	public List<R> getResultList() {
		return handleResultList();
	}

	protected void setParentBean(T parentBean) {
		this.parentBean = parentBean;
		// TODO contornar problemas de lazy exception.
		List<R> temp = new ArrayList<R>(handleResultList());
		handleResultList().clear();
		handleResultList().addAll(temp);
	}

	/**
	 * 
	 * -- Instanciação das classes genéricas --
	 * 
	 */

	protected T getParentBean() {
		if (parentBean == null) {
			parentBean = Reflections.instantiate(this.getBeanClass());
		}
		return parentBean;
	}

	protected Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}

	protected R getSelectedBean() {
		if (selectedBean == null) {
			selectedBean = Reflections.instantiate(this.getSelectedBeanClass());
		}
		return selectedBean;
	}

	protected Class<R> getSelectedBeanClass() {
		if (this.selectedBeanClass == null) {
			this.selectedBeanClass = Reflections.getGenericTypeArgument(this.getClass(), 1);
		}

		return this.selectedBeanClass;
	}

}
