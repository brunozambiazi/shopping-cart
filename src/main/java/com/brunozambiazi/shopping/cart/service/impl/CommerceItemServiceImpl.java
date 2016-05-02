package com.brunozambiazi.shopping.cart.service.impl;

import com.brunozambiazi.shopping.cart.dao.CommerceItemDao;
import com.brunozambiazi.shopping.cart.dto.ShoppingCart;
import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import com.brunozambiazi.shopping.cart.entity.Product;
import com.brunozambiazi.shopping.cart.exception.InvalidCommerceItemException;
import com.brunozambiazi.shopping.cart.exception.InvalidProductException;
import com.brunozambiazi.shopping.cart.exception.InvalidUserSessionException;
import com.brunozambiazi.shopping.cart.interceptor.UserSession;
import com.brunozambiazi.shopping.cart.service.CommerceItemService;
import com.brunozambiazi.shopping.cart.service.ProductService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommerceItemServiceImpl implements CommerceItemService {

	private static final Logger LOG = LoggerFactory.getLogger(CommerceItemServiceImpl.class);

	@Autowired
	private CommerceItemDao dao;

	@Autowired
	private ProductService productService;

	@Override
	public Collection<CommerceItem> findAll() {
		LOG.debug("Finding all commerce items");
		Collection<CommerceItem> items = dao.findAll();
		LOG.trace(" .. items: {}", items);
		return items;
	}

	@Override
	public CommerceItem findById(String id) {
		LOG.debug("Finding commerce item by ID {}", id);

		if (StringUtils.isBlank(id)) {
			throw new InvalidCommerceItemException("The informed item is invalid.");
		}

		CommerceItem entity = dao.findById(id);
		LOG.debug(" .. item: {}", entity);
		return entity;
	}

	@Override
	public ShoppingCart mountShoppingCart(@UserSession String user) {
		LOG.debug("Mounting shopping cart for user {}", user);

		final ShoppingCart cart = new ShoppingCart();
		for (CommerceItem element : dao.findByUser(user)) {
			LOG.debug(" .. adding item {}", element);
			cart.add(element);
		}

		LOG.debug(" .. shopping cart ready: {}", cart);
		return cart;
	}

	@Override
	public void remove(String id, @UserSession String user) throws InvalidCommerceItemException, InvalidUserSessionException {
		LOG.debug("Removing item {} from user {}", id, user);

		CommerceItem item = findById(id);
		if (item == null) {
			throw new InvalidCommerceItemException("The informed item doesn't exist.");
		}

		if (! user.equalsIgnoreCase(item.getUser())) {
			LOG.error("The item {} (of the user {}) doesn't belong to the informed user {}", item, item.getUser(), user);
			throw new InvalidUserSessionException();
		}

		dao.remove(item);
		LOG.debug(" .. item {} removed", id);
	}

	@Override
	public CommerceItem save(String productId, Integer quantity, @UserSession String user) throws InvalidProductException {
		LOG.debug("Saving {} item(s) of product {} for user {}", quantity, productId, user);

		final Product product = productService.findById(productId);
		if (product == null) {
			throw new InvalidProductException("The informed product doesn't exist.");
		}

		if (quantity == null || quantity < 1) {
			throw new InvalidProductException("The product quantity is invalid.");
		}

		LOG.debug("Verifying if there is one commerce item for user {} and product {}", user, product.getId());
		CommerceItem entity = dao.findByUserAndProduct(user, product);

		if (entity == null) {
			LOG.debug(" .. there isn't. Inserting a new one.");
			entity = insert(product, quantity, user);
			LOG.debug(" .. item inserted");
		} else {
			LOG.debug(" .. there is: {}. Updating.", entity);
			entity = update(entity, quantity);
			LOG.debug(" .. item updated");
		}

		return entity;
	}

	/**
	 * Create a new commerce item with the informed product, quantity and user.
	 */
	private CommerceItem insert(Product product, Integer quantity, String user) {
		CommerceItem entity = new CommerceItem();
		entity.setId(UUID.randomUUID().toString());
		entity.setProduct(product);
		entity.setQuantity(quantity);
		entity.setUser(user);
		return update(entity, quantity);
	}

	/**
	 * Update an existent commerce item with the proper quantity and amount of value.
	 */
	private CommerceItem update(CommerceItem entity, Integer quantity) {
		entity.setQuantity(quantity);
		entity.setAmount(entity.getProduct().getPrice().multiply(new BigDecimal(entity.getQuantity())));
		dao.save(entity);
		return entity;
	}

}
