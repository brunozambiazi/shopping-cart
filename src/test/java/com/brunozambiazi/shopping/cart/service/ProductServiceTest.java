package com.brunozambiazi.shopping.cart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.brunozambiazi.shopping.cart.dao.ProductDao;
import com.brunozambiazi.shopping.cart.entity.Product;
import com.brunozambiazi.shopping.cart.exception.InvalidProductException;
import com.brunozambiazi.shopping.cart.service.impl.ProductServiceImpl;
import java.util.ArrayList;
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
public class ProductServiceTest {

	@Mock
	private ProductDao dao;

	@InjectMocks
	private ProductServiceImpl service;

	@Test
	public void findAll_noOne() {
		when(dao.findAll()).thenReturn(Collections.EMPTY_LIST);

		assertTrue(CollectionUtils.isEmpty(service.findAll()));
	}

	@Test
	public void findAll_atLeastOne() {
		for (int x = 0; x < 10; x++) {
			int databaseSize = new Random().nextInt(100)+1;

			Collection<Product> products = new ArrayList<>();
			for (int y = 0; y < databaseSize; y++) {
				products.add(new Product());
			}

			when(dao.findAll()).thenReturn(products);
			assertEquals(databaseSize, service.findAll().size());
		}
	}

	@Test(expected = InvalidProductException.class)
	public void findById_nullId() {
		service.findById(null);
	}

	@Test(expected = InvalidProductException.class)
	public void findById_blankId() {
		service.findById(" ");
	}

	@Test
	public void findById_nonExistentId() {
		assertNull(service.findById("1234567890"));
	}

	@Test
	public void findById_validId() {
		Product p1 = new Product("1");
		when(dao.findOne("1")).thenReturn(p1);

		Product p2 = new Product("2");
		when(dao.findOne("2")).thenReturn(p2);

		Product p3 = new Product("3");
		when(dao.findOne("3")).thenReturn(p3);

		assertEquals(p1, service.findById("1"));
		assertEquals(p2, service.findById("2"));
		assertEquals(p3, service.findById("3"));
	}

}
