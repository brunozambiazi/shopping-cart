package com.brunozambiazi.shopping.cart.service.impl;

import com.brunozambiazi.shopping.cart.dao.ProductDao;
import com.brunozambiazi.shopping.cart.entity.Product;
import com.brunozambiazi.shopping.cart.exception.InvalidProductException;
import com.brunozambiazi.shopping.cart.service.ProductService;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao dao;

	@Override
	public Collection<Product> findAll() {
		LOG.debug("Finding all products");
		Collection<Product> items = dao.findAll();
		LOG.trace(" .. items: {}", items);
		return items;
	}

	@Override
	public Product findById(String id) {
		LOG.debug("Finding product by ID {}", id);

		if (StringUtils.isBlank(id)) {
			throw new InvalidProductException("The informed product is invalid.");
		}

		Product entity = dao.findById(id);
		LOG.debug(" .. product: {}", entity);
		return entity;
	}

}
