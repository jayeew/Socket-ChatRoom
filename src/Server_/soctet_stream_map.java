package Server_;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class soctet_stream_map<K, V> {
	public Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());

	public synchronized void removeByValue(Object value) {
		for (Object key : map.keySet()) {
			if (map.get(key) == value) {
				map.remove(key);
				break;
			}
		}
	}

	public synchronized Set<V> valueSet() {
		Set<V> result = new HashSet<V>();
		map.forEach((key, value) -> result.add(value));
		return result;
	}

	public synchronized K getKeyByValue(V val) {
		for (K key : map.keySet()) {
			if (map.get(key) == val || map.get(key).equals(val)) {
				return key;
			}
		}
		return null;
	}

	public synchronized V put(K  key, V value) {
//		for (V val : valueSet()) {
//			if (val.equals(value) && val.hashCode() == value.hashCode()) {
//				throw new RuntimeException("MyMap do not allow the Repet Value!");
//			}
//
//		}
		return map.put(key, value);
	}
}
