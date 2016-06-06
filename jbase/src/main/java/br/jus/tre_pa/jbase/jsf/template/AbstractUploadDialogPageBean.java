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

public abstract class AbstractUploadDialogPageBean<T, I> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T bean;

	private Class<T> beanClass;

	/**
	 * implementação deve constar a anotation @UIAction
	 */
	public abstract String load(Long id);

	/**
	 * implementação deve constar a anotation @Hide
	 */
	public abstract String update();

	protected abstract List<I> handleResultList();

	protected abstract I prepareUpload(FileUploadEvent fileUploaded);

	protected abstract byte[] getByteContent(I doc);

	protected abstract String getTypeContent(I doc);

	protected abstract String getNameContent(I doc);

	@Hide
	public void cancel() {

	}

	@UpdateBody
	public String delete(T doc) {
		handleResultList().remove(doc);
		return null;
	}

	@UpdateBody
	public String upload(FileUploadEvent fileUploaded) {
		I doc = prepareUpload(fileUploaded);
		handleResultList().add(doc);
		return null;
	}

	public StreamedContent prepareDownload(I doc) {
		InputStream stream = new ByteArrayInputStream(getByteContent(doc));
		return new DefaultStreamedContent(stream, getTypeContent(doc), getNameContent(doc));
	}

	public List<I> getResultList() {
		return handleResultList();
	}

	protected Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}

	protected void setBean(T bean) {
		this.bean = bean;
		// TODO contornar problemas de lazy exception.
		List<I> temp = new ArrayList<I>(handleResultList());
		handleResultList().clear();
		handleResultList().addAll(temp);
	}

	public T getBean() {
		if (bean == null) {
			bean = Reflections.instantiate(this.getBeanClass());
		}
		return bean;
	}

}
