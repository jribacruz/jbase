package br.jus.tre_pa.jbase.filter.internal;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

public class JPQLBuilderHelper {

	public static String getProjectionAttributesAsString(List<String> projectionAttributes) {
		return StringUtils.join(projectionAttributes, ",");
	}
	
	public static String getOrderByAttributesAsString(List<String> orderByAttributes) {
		return StringUtils.join(orderByAttributes, ",");
	}

	public static String replace(String exp, Map<String, String> params) {
		return new StrSubstitutor(params).replace(exp);
	}

}
