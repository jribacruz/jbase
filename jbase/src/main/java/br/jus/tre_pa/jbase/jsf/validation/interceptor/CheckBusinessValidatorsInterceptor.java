package br.jus.tre_pa.jbase.jsf.validation.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.jus.tre_pa.jbase.jsf.validation.annotation.CheckBusinessValidators;

@Interceptor
@CheckBusinessValidators
public class CheckBusinessValidatorsInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1222146161745214626L;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		Object ret = null;
		ret = ic.proceed();
		
		return ret;
	}

}
