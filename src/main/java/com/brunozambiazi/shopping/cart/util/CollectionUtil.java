package com.brunozambiazi.shopping.cart.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import org.springframework.util.CollectionUtils;

public final class CollectionUtil {

	private CollectionUtil() {
	}

	/**
	 * Search all the elements in the informed collection,
	 *   that corresponds to the informed predicate rule.
	 *
	 * @param predicate The logic that matches the object.
	 * @param collection The whole collection of elements.
	 * @return The collection with matched objects.
	 */
	public static <T> Collection<T> findAll(Predicate<T> predicate, Collection<T> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return null;
		}

		Collection<T> result = new LinkedHashSet<T>();

		for (T element : collection) {
			if (predicate.evaluate(element)) {
				result.add(element);
			}
		}

		return result;
	}
	/**
	 * Search for the first element in the informed collection,
	 *   that corresponds to the informed predicate rule.
	 *
	 * @param predicate The logic that matches the object.
	 * @param collection The whole collection of elements.
	 * @return The object found or null.
	 */
	public static <T> T findIn(Predicate<T> predicate, Collection<T> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return null;
		}

		for (T element : collection) {
			if (predicate.evaluate(element)) {
				return element;
			}
		}

		return null;
	}

	public static interface Predicate<T> {

		boolean evaluate(T object);

	}

}
