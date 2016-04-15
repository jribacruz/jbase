package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.primefaces.model.DualListModel;

/**
 * 
 * @author jcruz
 *
 * @param <T>
 * @param <R>
 */
public abstract class AbstractSingleSelectionDialogPageBean<T, R> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private DualListModel<R> model = new DualListModel<R>();

	/**
	 * 
	 */
	private T bean;

	/**
	 * 
	 * @return
	 */
	protected abstract List<R> handleTarget();

	/**
	 * 
	 * @return
	 */
	protected abstract List<R> handleSource();

	/**
	 * 
	 * @param bean
	 */
	protected abstract void onCancelBean(T bean);

	public String load(T bean) {
		this.bean = bean;
		model.setTarget(new ArrayList<R>(handleTarget()));
		if (model.getSource().isEmpty()) {
			model.setSource(handleSource());
			model.getSource().removeAll(model.getTarget());
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String done() {
		addSelectedsToBean();
		removeDeselectedsFromBean();
		return null;
	}

	/**
	 * 
	 */
	public void cancel() {
		clear();
	}

	protected void clear() {
		model.getSource().clear();
		model.getTarget().clear();
	}

	/**
	 * 
	 * @param item
	 */
	public void addTargetRemoveSource(R item) {
		model.getSource().remove(item);
		model.getTarget().add(item);
	}

	/**
	 * 
	 * @param item
	 */
	public void removeTargetAddSource(R item) {
		model.getTarget().remove(item);
		model.getSource().add(item);
	}

	/**
	 * 
	 */
	private void addSelectedsToBean() {
		ListIterator<R> iter = model.getTarget().listIterator();
		while (iter.hasNext()) {
			R item = iter.next();
			if (!handleTarget().contains(item)) {
				handleTarget().add(item);
			}
		}
	}

	/**
	 * 
	 */
	private void removeDeselectedsFromBean() {
		ListIterator<R> iter = handleTarget().listIterator();
		while (iter.hasNext()) {
			R item = iter.next();
			if (!model.getTarget().contains(item)) {
				iter.remove();
			}
		}
	}

	public DualListModel<R> getModel() {
		return model;
	}

	public T getBean() {
		return bean;
	}

}
