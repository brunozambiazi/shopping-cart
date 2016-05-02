package com.brunozambiazi.shopping.cart.service;

import com.brunozambiazi.shopping.cart.entity.BaseEntity;
import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T extends BaseEntity<K>, K extends Serializable> {

	/**
	 * Search and return all products in the repository.
	 * @return A collection of all products.
	 */
	Collection<T> findAll();

	/**
	 * Find an entity by its id.
	 * @param id The value to search from.
	 * @return The entity with informed id, if exists in repository. Null otherwise.
	 */
	T findById(K id);

}
