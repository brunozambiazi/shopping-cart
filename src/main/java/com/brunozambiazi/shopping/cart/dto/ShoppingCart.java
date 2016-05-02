package com.brunozambiazi.shopping.cart.dto;

import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class ShoppingCart {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCart.class);

	private final Collection<CommerceItem> items;
	private BigDecimal amount;

	public ShoppingCart() {
		items = new LinkedHashSet<>();
		calculateAmount();
	}

	public void add(final CommerceItem item) {
		LOG.debug("Adding item to shopping cart: {}", item);

		items.add(item);
		calculateAmount();
	}

	public void calculateAmount() {
		LOG.debug("Calculating amount of shopping cart ..");

		amount = BigDecimal.ZERO;
		for (CommerceItem item : items) {
			amount = amount.add(item.getAmount());
		}

		LOG.debug(" .. amount: {}", amount);
	}

}
