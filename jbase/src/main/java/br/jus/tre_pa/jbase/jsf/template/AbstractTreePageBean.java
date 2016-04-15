package br.jus.tre_pa.jbase.jsf.template;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.jbase.jsf.workflow.base.EventTargetBean;

/**
 * 
 * @author jcruz
 *
 * @param <T>
 * @param <I>
 */
public abstract class AbstractTreePageBean<T, I> implements Serializable, EventTargetBean<T>, Selectable<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TreeNode root;

	private Class<T> beanClass;

	private TreeNode selectedNode;

	private T selectedBean;

	protected abstract List<T> handleResultList();

	protected abstract String getTreeType();

	/**
	 * Transforma a coleção de beans do tipo T em objetos TreeNode (Nodos de
	 * primeiro nível)
	 * 
	 * @param beans
	 *            Coleção de beans do tipo T proveniente do handleResultList()
	 */
	private void handleRootTreeNode(Collection<T> beans) {
		this.root = new DefaultTreeNode(Reflections.instantiate(getBeanClass()), null);
		for (T child : beans) {
			TreeNode parent = new DefaultTreeNode(getTreeType(), child, this.root);
			handleTreeNode(parent, child);
		}
	}

	protected void handleTreeNode(TreeNode parentNode, T parentBean) {
	}

	public TreeNode getRoot() {
		if (root == null) {
			handleRootTreeNode(handleResultList());
		}
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	protected Class<T> getBeanClass() {
		if (this.beanClass == null) {
			this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}

	public void clear() {
		this.root = null;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	@Override
	public T getSelectedBean() {
		if (this.selectedBean == null) {
			this.selectedBean = Reflections.instantiate(getBeanClass());
		}
		return null;
	}

}
