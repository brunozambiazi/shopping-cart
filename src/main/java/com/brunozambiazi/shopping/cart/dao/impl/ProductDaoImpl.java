package com.brunozambiazi.shopping.cart.dao.impl;

import com.brunozambiazi.shopping.cart.dao.ProductDao;
import com.brunozambiazi.shopping.cart.entity.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends GenericBaseDao<Product, String> implements ProductDao {

	private static final Map<String, Product> DATABASE = new HashMap<>();

	@Override
	protected Map<String, Product> getData() {
		return DATABASE;
	}

}
