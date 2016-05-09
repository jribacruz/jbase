package br.jus.tre_pa.jbase.filter.internal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import br.gov.frameworkdemoiselle.util.Strings;
import br.jus.tre_pa.jbase.filter.annotation.FilterAttribute;
import br.jus.tre_pa.jbase.filter.annotation.FilterBean;
import br.jus.tre_pa.jbase.filter.annotation.FilterPath;

public class FilterBeanModel {

	private Object filterBean;
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
	private List<Map<String, String>> paths;

	/**
	 * 
	 */
	private List<FilterBeanAttributeModel> whereAttributes;

	/**
	 * 
	 */
	private String alias;

	public FilterBeanModel(Object filterBean) {
		super();
		this.filterBean = filterBean;
	}

	/**
	 * Retorna os atributos da projeção.
	 * 
	 * @return
	 */
	public List<String> getProjectionAttributes() {
		if (this.projectionAttributes == null) {
			this.projectionAttributes = new ArrayList<String>();
			for (String attribute : filterBean.getClass().getAnnotation(FilterBean.class).projection()) {
				if (!Strings.isEmpty(attribute)) {
					if (attribute.startsWith(getAlias().concat(".")) || attribute.contains(".")) {
						projectionAttributes.add(attribute);
						continue;
					}
					projectionAttributes.add(getAlias().concat(".").concat(attribute));
				}
			}
		}
		return projectionAttributes;
	}

	public String getProjectionAttributesAsString() {
		return StringUtils.join(getProjectionAttributes(), ",");
	}

	/**
	 * 
	 * @return
	 */
	public String getEntityName() {
		return this.filterBean.getClass().getAnnotation(FilterBean.class).entity().getSimpleName();
	}

	/**
	 * 
	 * Retorna o alias da clausula 'from'
	 * 
	 * @return
	 */
	public String getAlias() {
		if (this.alias == null) {
			this.alias = this.filterBean.getClass().getAnnotation(FilterBean.class).alias();
		}
		return this.alias;
	}

	/**
	 * Retorna os atributos da ordenação.
	 * 
	 * @return
	 */
	public List<String> getOrderByAttributes() {
		if (this.orderByAttributes == null) {
			this.orderByAttributes = new ArrayList<String>();
			for (String attribute : filterBean.getClass().getAnnotation(FilterBean.class).orderBy()) {
				if (!Strings.isEmpty(attribute)) {
					if (attribute.startsWith(getAlias().concat(".")) || attribute.contains(".")) {
						orderByAttributes.add(attribute);
						continue;
					}
					orderByAttributes.add(getAlias().concat(".").concat(attribute));
				}
			}
		}
		return orderByAttributes;
	}

	/**
	 * 
	 * @return
	 */
	public String getOrderByAttributesAsString() {
		return StringUtils.join(getOrderByAttributes(), ",");
	}

	/**
	 * 
	 * @return
	 */
	public List<Map<String, String>> getPaths() {
		if (this.paths == null) {
			this.paths = new ArrayList<Map<String, String>>();
			for (FilterPath path : filterBean.getClass().getAnnotation(FilterBean.class).paths()) {
				Map<String, String> pathMap = new HashMap<String, String>();
				pathMap.put(path.alias(), path.path());
				paths.add(pathMap);
			}
		}
		return paths;
	}

	/**
	 * 
	 * @return
	 */
	public List<FilterBeanAttributeModel> getWhereAttributes() {
		if (this.whereAttributes == null) {
			this.whereAttributes = new ArrayList<FilterBeanAttributeModel>();
			for (Map.Entry<String, String> entry : describeFilterBean().entrySet()) {
				Field field = getFieldFromFilterBean(entry.getKey());
				if (field.isAnnotationPresent(FilterAttribute.class)) {
					this.whereAttributes.add(new FilterBeanAttributeModel(field));
				}
			}
		}
		return whereAttributes;
	}

	private Field getFieldFromFilterBean(String name) {
		try {
			return this.filterBean.getClass().getField(name);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> describeFilterBean() {
		try {
			return BeanUtils.describe(this.filterBean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return new HashMap<String, String>();
	}

}
