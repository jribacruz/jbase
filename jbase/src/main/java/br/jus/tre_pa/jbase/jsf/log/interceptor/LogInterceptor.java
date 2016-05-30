package br.jus.tre_pa.jbase.jsf.log.interceptor;

import java.io.Serializable;
import java.lang.reflect.Type;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.jus.tre_pa.jbase.jsf.log.annotation.Log;

/**
 * 
 * @author jcruz
 *
 */
@Interceptor
@Log
public class LogInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger log = LoggerFactory.getLogger(LogInterceptor.class);

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		// Hora de inicio da chamada do método.
		DateTime timeStart = new DateTime();

		Object ret = null;
		String logInit = buildStrInit(ret, ic);
		printLog(ic, logInit);
		ret = ic.proceed();

		// Hora de fim da chamada do método.
		DateTime timeStop = new DateTime();
		String logReturn = buildStrReturn(ret, ic, timeStart, timeStop);
		printLog(ic, logReturn);

		return ret;
	}

	/*
	 * Monta as informações de inicialização de chamada do método: Nome do
	 * Método, Lista de Parametros com o tipo, nome e o valor correspondente.
	 */
	private String buildStrInit(Object ret, InvocationContext ic) {
		StringBuilder sb = new StringBuilder();
		sb.append("Informações de chamada do método: " + getMethodName(ic));
		sb.append("\n%\t");
		/* Nome da classe */
		sb.append("[class: " + ic.getTarget().getClass().getSimpleName());
		sb.append("\n%\t\t");
		/* Nome do método */
		sb.append("method => " + getMethodName(ic));
		sb.append("\n%\t\t");
		sb.append("params => (");
		sb.append("\n%\t\t\t");
		sb.append(getParams(ic));
		sb.append("\n%\t\t");
		sb.append(")");
		sb.append("\n%\t");
		sb.append("]");
		return sb.toString();
	}

	/*
	 * Monta as informacões de retorno do método: Nome do método
	 */
	private String buildStrReturn(Object ret, InvocationContext ic, DateTime timeStart, DateTime timeStop) {
		StringBuilder sb = new StringBuilder();
		sb.append("Informações de retorno do método: " + getMethodName(ic));
		sb.append("\n%\t");
		sb.append("[class: " + ic.getTarget().getClass().getSimpleName());
		sb.append("\n%\t\t");
		sb.append("method => " + getMethodName(ic));
		sb.append("\n%\t\t");
		sb.append("return => " + getReturn(ret, ic));
		sb.append("\n%\t\t");
		sb.append("time   => " + getElapsedTime(timeStart, timeStop));
		sb.append("\n%\t");
		sb.append("]");
		return sb.toString();
	}

	private String getMethodName(InvocationContext ic) {
		return ic.getMethod().getName();
	}

	private String getElapsedTime(DateTime timeStart, DateTime timeStop) {
		// Minutes minutes = Minutes.minutesBetween(timeStart, timeStop);
		// Seconds seconds = Seconds.secondsBetween(timeStart, timeStop);
		Duration duration = new Duration(timeStart, timeStop);
		StringBuilder sb = new StringBuilder();
		if (duration.getStandardMinutes() > 0) {
			sb.append(duration.getStandardMinutes() + "min ");
		}
		if (duration.getStandardSeconds() > 0) {
			sb.append(duration.getStandardSeconds() + "s");
		}
		sb.append(duration.getMillis() + "ms");
		return sb.toString();
	}

	private String getReturn(Object ret, InvocationContext ic) {
		Type type = ic.getMethod().getGenericReturnType();
		StringBuilder sb = new StringBuilder();
		sb.append(ic.getMethod().getReturnType().getSimpleName());
		return sb.toString();
	}

	private String getParams(InvocationContext ic) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ic.getMethod().getParameterTypes().length; i++) {
			Class<?> paramType = ic.getMethod().getParameterTypes()[i];
			// TypeVariable<Method> typeVariable =
			// ic.getMethod().getTypeParameters()[i];

			Object param = ic.getParameters()[i];
			// sb.append(typeVariable.getName());
			sb.append(":");
			sb.append(param.getClass().getSimpleName());
		}
		return sb.toString();
	}

	private void printLog(InvocationContext ic, String logStr) {
		log.debug(logStr);
	}
}
