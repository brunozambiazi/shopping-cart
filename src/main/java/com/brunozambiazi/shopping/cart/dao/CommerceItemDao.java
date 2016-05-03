package com.brunozambiazi.shopping.cart.dao;

import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import com.brunozambiazi.shopping.cart.entity.Product;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

public interface CommerceItemDao extends CrudRepository<CommerceItem, String> {

	Collection<CommerceItem> findByUser(String user);

	CommerceItem findByUserAndProduct(String user, Product product);

}
