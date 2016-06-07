package br.jus.tre_pa.jbase.jsf.template;

import org.primefaces.event.FileUploadEvent;

public interface Uploadable<T, R> {

	public R prepareUpload(FileUploadEvent fileUploaded, T parent);

	public byte[] getByteContent();

	public String getTypeContent();

	public String getNameContent();

}
