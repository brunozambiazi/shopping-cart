package com.brunozambiazi.shopping.cart.dao.impl;

import com.brunozambiazi.shopping.cart.dao.CommerceItemDao;
import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import com.brunozambiazi.shopping.cart.entity.Product;
import com.brunozambiazi.shopping.cart.util.CollectionUtil;
import com.brunozambiazi.shopping.cart.util.CollectionUtil.Predicate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class CommerceItemDaoImpl extends GenericBaseDao<CommerceItem, String> implements CommerceItemDao {

	private static final Map<String, CommerceItem> DATABASE = new HashMap<>();

	@Override
	protected Map<String, CommerceItem> getData() {
		return DATABASE;
	}

	@Override
	public Collection<CommerceItem> findByUser(final String user) {
		return CollectionUtil.findAll(new Predicate<CommerceItem>() {

			@Override
			public boolean evaluate(CommerceItem object) {
				return user.equalsIgnoreCase(object.getUser());
			}

		}, findAll());
	}

	@Override
	public CommerceItem findByUserAndProduct(String user, final Product product) {
		return CollectionUtil.findIn(new Predicate<CommerceItem> () {

			@Override
			public boolean evaluate(CommerceItem object) {
				return product.getId().equals(object.getProduct().getId());
			}

		}, findByUser(user));
	}

	@Override
	public void remove(CommerceItem entity) {
		getData().remove(entity.getId());
	}

}
