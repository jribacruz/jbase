package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import br.jus.tre_pa.jbase.jsf.workflow.annotation.Hide;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.Show;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateBody;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateFooter;
import br.jus.tre_pa.jbase.jsf.workflow.annotation.UpdateHeader;

/**
 * 
 * @author jcruz
 *
 * @param <T>
 * @param <R>
 */
public abstract class AbstractMultipleSelectionDialogPageBean<T, R> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

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

	@Show
	@UpdateHeader
	@UpdateBody
	@UpdateFooter
	public String load(T bean) {
		log.debug("[load] Carregando {}", bean);
		this.bean = bean;
		initTarget();
		initSource();
		return null;
	}

	private void initSource() {
		if (model.getSource().isEmpty()) {
			model.setSource(handleSource());
			model.getSource().removeAll(model.getTarget());
		}
	}

	private void initTarget() {
		List<R> cache = new ArrayList<R>();
		for (int i = 0; i < handleTarget().size(); i++) {
			cache.add(handleTarget().get(i));
		}
		model.setTarget(new ArrayList<R>(cache));
	}

	/**
	 * 
	 * @return
	 */
	@Hide
	public String done() {
		removeDeselectedsFromBean();
		addSelectedsToBean();
		return null;
	}

	protected void clear() {
		model.getSource().clear();
		model.getTarget().clear();
	}

	/**
	 * 
	 * @param item
	 */
	@UpdateBody
	public void addTargetRemoveSource(R item) {
		model.getSource().remove(item);
		model.getTarget().add(item);
	}

	/**
	 * 
	 * @param item
	 */
	@UpdateBody
	public void removeTargetAddSource(R item) {
		model.getTarget().remove(item);
		model.getSource().add(item);
	}
	
	@UpdateBody
	public String selectAll() {
		model.getTarget().addAll(model.getSource());
		model.getSource().clear();
		return null;
	}
	
	@UpdateBody
	public String deselectAll() {
		model.getSource().addAll(model.getTarget());
		model.getTarget().clear();
		return null;
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
		if (handleTarget().size() > 0) {
			ListIterator<R> iter = handleTarget().listIterator();
			while (iter.hasNext()) {
				R item = iter.next();
				if (!model.getTarget().contains(item)) {
					iter.remove();
					log.debug("[removeDeselectedsFromBean] Item removido: {}", item);
				}
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
