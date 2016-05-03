package com.brunozambiazi.shopping.cart.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

public class ShoppingCartTest {

	@Test
	public void newInstance_amount() {
		ShoppingCart cart = new ShoppingCart();
		assertTrue(CollectionUtils.isEmpty(cart.getItems()));
		assertEquals(BigDecimal.ZERO, cart.getAmount());
	}

	@Test
	public void add_amount() {
		CommerceItem item1 = new CommerceItem();
		item1.setAmount(BigDecimal.ONE);

		CommerceItem item2 = new CommerceItem();
		item2.setAmount(BigDecimal.TEN);

		ShoppingCart cart = new ShoppingCart();

		cart.add(item1);
		assertEquals(1, cart.getItems().size());
		assertEquals(BigDecimal.ONE, cart.getAmount());

		cart.add(item2);
		assertEquals(2, cart.getItems().size());
		assertEquals(new BigDecimal(11), cart.getAmount());
	}

	@Test
	public void add_mustCalculateAmount() {
		CommerceItem item = new CommerceItem();
		item.setAmount(BigDecimal.ONE);

		ShoppingCart cart = spy(new ShoppingCart());
		cart.add(item);

		verify(cart, times(1)).calculateAmount();
	}

}
