package com.brunozambiazi.shopping.cart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.brunozambiazi.shopping.cart.dao.CommerceItemDao;
import com.brunozambiazi.shopping.cart.dto.ShoppingCart;
import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import com.brunozambiazi.shopping.cart.entity.Product;
import com.brunozambiazi.shopping.cart.exception.InvalidCommerceItemException;
import com.brunozambiazi.shopping.cart.exception.InvalidProductException;
import com.brunozambiazi.shopping.cart.exception.InvalidUserSessionException;
import com.brunozambiazi.shopping.cart.service.impl.CommerceItemServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class CommerceItemServiceTest {

	@Mock
	private CommerceItemDao dao;

	@Mock
	private ProductService productService;

	@InjectMocks
	private CommerceItemServiceImpl service;

	@Test
	public void findAll_noOne() {
		when(dao.findAll()).thenReturn(Collections.EMPTY_LIST);

		assertTrue(CollectionUtils.isEmpty(service.findAll()));
	}

	@Test
	public void findAll_atLeastOne() {
		for (int x = 0; x < 10; x++) {
			int databaseSize = new Random().nextInt(100)+1;

			Collection<CommerceItem> items = new ArrayList<>();
			for (int y = 0; y < databaseSize; y++) {
				items.add(new CommerceItem());
			}

			when(dao.findAll()).thenReturn(items);
			assertEquals(databaseSize, service.findAll().size());
		}
	}

	@Test(expected = InvalidCommerceItemException.class)
	public void findById_nullId() {
		service.findById(null);
	}

	@Test(expected = InvalidCommerceItemException.class)
	public void findById_blankId() {
		service.findById(" ");
	}

	@Test
	public void findById_nonExistentId() {
		assertNull(service.findById("1234567890"));
	}

	@Test
	public void findById_validId() {
		CommerceItem p1 = new CommerceItem("1");
		when(dao.findOne("1")).thenReturn(p1);

		CommerceItem p2 = new CommerceItem("2");
		when(dao.findOne("2")).thenReturn(p2);

		CommerceItem p3 = new CommerceItem("3");
		when(dao.findOne("3")).thenReturn(p3);

		assertEquals(p1, service.findById("1"));
		assertEquals(p2, service.findById("2"));
		assertEquals(p3, service.findById("3"));
	}

	@Test
	public void mountShoppingCart_noItems() {
		ShoppingCart cart = service.mountShoppingCart("user");
		assertNotNull(cart);
		assertTrue(CollectionUtils.isEmpty(cart.getItems()));
		assertEquals(BigDecimal.ZERO, cart.getAmount());
	}

	@Test
	public void mountShoppingCart_oneItem() {
		CommerceItem item1 = new CommerceItem();
		item1.setAmount(BigDecimal.TEN);

		when(dao.findByUser("user")).thenReturn(Arrays.asList(item1));

		ShoppingCart cart = service.mountShoppingCart("user");
		assertNotNull(cart);
		assertEquals(1, cart.getItems().size());
		assertEquals(item1.getAmount(), cart.getAmount());
	}

	@Test
	public void mountShoppingCart_threeItems() {
		CommerceItem item1 = new CommerceItem();
		item1.setAmount(BigDecimal.TEN);

		CommerceItem item2 = new CommerceItem();
		item2.setAmount(BigDecimal.ONE);

		CommerceItem item3 = new CommerceItem();
		item3.setAmount(BigDecimal.ONE);

		when(dao.findByUser("user")).thenReturn(Arrays.asList(item1, item2, item3));

		ShoppingCart cart = service.mountShoppingCart("user");
		assertNotNull(cart);
		assertEquals(3, cart.getItems().size());
		assertEquals(new BigDecimal(12), cart.getAmount());
	}

	@Test(expected = InvalidCommerceItemException.class)
	public void remove_nullItem() {
		service.remove(null, "user");
	}

	@Test(expected = InvalidCommerceItemException.class)
	public void remove_blankItem() {
		service.remove(" ", "user");
	}

	@Test(expected = InvalidUserSessionException.class)
	public void remove_invalidUser() {
		CommerceItem item = new CommerceItem("1");
		item.setUser("otheruser");

		when(dao.findOne("1")).thenReturn(item);

		service.remove("1", "user");
	}

	@Test
	public void remove() {
		CommerceItem item = new CommerceItem("1");
		item.setUser("user");

		when(dao.findOne("1")).thenReturn(item);

		service.remove("1", "user");
	}

	@Test(expected = InvalidProductException.class)
	public void save_nullProduct() {
		service.save(null, 1, "user");
	}

	@Test(expected = InvalidProductException.class)
	public void save_blankProduct() {
		service.save(" ", 1, "user");
	}

	@Test(expected = InvalidProductException.class)
	public void save_invalidProduct() {
		service.save("1", 1, "user");
	}

	@Test(expected = InvalidProductException.class)
	public void save_nullQuantity() {
		service.save("product", null, "user");
	}

	@Test(expected = InvalidProductException.class)
	public void save_zeroQuantity() {
		service.save("product", 0, "user");
	}

	@Test
	public void save_insert() {
		Product product = new Product();
		product.setPrice(BigDecimal.ONE);

		when(productService.findById("1")).thenReturn(product);

		CommerceItem item = service.save("1", 1, "user");
		verify(dao).save(any(CommerceItem.class));

		assertNotNull(item);
		assertEquals(new Integer(1), item.getQuantity());
		assertEquals(BigDecimal.ONE, item.getAmount());

	}

	@Test
	public void save_update() {
		Product product = new Product("1");
		product.setPrice(BigDecimal.ONE);

		CommerceItem item = new CommerceItem("1");
		item.setUser("user");
		item.setProduct(product);

		when(productService.findById("1")).thenReturn(product);
		when(dao.findByUserAndProduct("user", product)).thenReturn(item);

		CommerceItem savedItem = service.save("1", 10, "user");
		verify(dao).save(any(CommerceItem.class));

		assertNotNull(savedItem);
		assertEquals(item, savedItem);
		assertEquals(new Integer(10), item.getQuantity());
		assertEquals(BigDecimal.TEN, item.getAmount());
	}

}
