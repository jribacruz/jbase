package br.jus.tre_pa.jbase.filter.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class FilterModel {

	/**
	 * 
	 */
	private String entityClassName;

	/**
	 * 
	 */
	private List<String> projectionAttributes;

	/**
	 * 
	 */
	private List<String> orderByAttributes;

	/**
	 * 
	 */
	private Multimap<Field, FilterAttributeModel> predicateAttributes;

	/**
	 * 
	 */
	private String alias;

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	public List<String> getProjectionAttributes() {
		if (this.projectionAttributes == null) {
			this.projectionAttributes = new ArrayList<String>();
		}
		return projectionAttributes;
	}

	public void setProjectionAttributes(List<String> projectionAttributes) {
		this.projectionAttributes = projectionAttributes;
	}

	public List<String> getOrderByAttributes() {
		if (this.orderByAttributes == null) {
			this.orderByAttributes = new ArrayList<String>();
		}
		return orderByAttributes;
	}

	public void setOrderByAttributes(List<String> orderByAttributes) {
		this.orderByAttributes = orderByAttributes;
	}

	public Multimap<Field, FilterAttributeModel> getPredicateAttributes() {
		if (this.predicateAttributes == null) {
			this.predicateAttributes = ArrayListMultimap.create();
		}
		return predicateAttributes;
	}

	public void setPredicateAttributes(Multimap<Field, FilterAttributeModel> predicateAttributes) {
		this.predicateAttributes = predicateAttributes;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
