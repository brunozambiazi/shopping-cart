package com.brunozambiazi.shopping.cart.dao;

import com.brunozambiazi.shopping.cart.entity.BaseEntity;
import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<T extends BaseEntity<K>, K extends Serializable> {

	Collection<T> findAll();

	T findById(K id);

	void save(T entity);

}
