package com.brunozambiazi.shopping.cart.dao;

import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import com.brunozambiazi.shopping.cart.entity.Product;
import java.util.Collection;

public interface CommerceItemDao extends BaseDao<CommerceItem, String> {

	Collection<CommerceItem> findByUser(String user);

	CommerceItem findByUserAndProduct(String user, Product product);

	void remove(CommerceItem entity);

}
