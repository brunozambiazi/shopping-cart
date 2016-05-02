package com.brunozambiazi.shopping.cart.dao.impl;

import com.brunozambiazi.shopping.cart.dao.BaseDao;
import com.brunozambiazi.shopping.cart.entity.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

abstract class GenericBaseDao<T extends BaseEntity<K>, K extends Serializable> implements BaseDao<T, K> {

	protected abstract Map<K, T> getData();

	@Override
	public Collection<T> findAll() {
		return getData().values();
	}

	@Override
	public T findById(K id) {
		return getData().get(id);
	}

	@Override
	public void save(T entity) {
		getData().put(entity.getId(), entity);
	}

}
