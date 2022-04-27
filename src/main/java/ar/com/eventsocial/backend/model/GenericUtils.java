package ar.com.eventsocial.backend.model;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenericUtils {

	public static <T> T callAndThrow(Supplier<T> action, Function<Exception, RuntimeException> exception) {
		try {
			return action.get();
		} catch (Exception ex) {
			throw exception.apply(ex);
		}
	}
	
	public static Object getValueFromKeyValue(Map<?, ?> hm, Object key) {
		for (Object o : hm.keySet()) {
			if (o != null && o.equals(key)) {
				return hm.get(o);
			}
		}
		return null;
	}
	
}
