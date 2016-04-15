package br.jus.tre_pa.jbase.jsf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListCheckboxModel<R> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<R> fullList;

	private List<R> selectedList;

	private Map<R, Boolean> model = new HashMap<R, Boolean>();

	public ListCheckboxModel(List<R> fullList, List<R> selectedList) {
		super();
		this.fullList = fullList;
		this.selectedList = selectedList;
		initMap();
		checkSelecteds();
	}
	
	/*
	public ListCheckboxModel(List<R> fullList) {
		super();
		this.fullList = fullList;
		this.selectedList = new ArrayList<R>();
		initMap();
		checkSelecteds();
	}*/

	private void initMap() {
		for (R item : fullList) {
			model.put(item, false);
		}
	}

	private void checkSelecteds() {
		Iterator<R> iter = selectedList.iterator();
		while (iter.hasNext()) {
			R item = iter.next();
			model.put(item, true);
		}
	}

	public void updateMapToList() {
		Iterator<R> iter = model.keySet().iterator();
		while (iter.hasNext()) {
			R item = iter.next();
			if (model.get(item)) {
				if (!selectedList.contains(item)) {
					selectedList.add(item);
				}
			} else {
				selectedList.remove(item);
			}
		}
	}

	public void clear() {
		fullList.clear();
		selectedList.clear();
		model.clear();
	}

	public Map<R, Boolean> getModel() {
		return model;
	}

	public void setModel(Map<R, Boolean> model) {
		this.model = model;
	}

	public List<R> getFullList() {
		return fullList;
	}

}
