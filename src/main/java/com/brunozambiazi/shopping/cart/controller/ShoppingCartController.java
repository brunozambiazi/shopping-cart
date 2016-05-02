package com.brunozambiazi.shopping.cart.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.brunozambiazi.shopping.cart.dto.ShoppingCart;
import com.brunozambiazi.shopping.cart.service.CommerceItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/shoppingcart", produces = APPLICATION_JSON_VALUE)
public class ShoppingCartController {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartController.class);
	private static final String COOKIE = "Cookie";

	@Autowired
	private CommerceItemService commerceItemService;

	@RequestMapping(method = GET)
	public ShoppingCart getShoppingCart(@RequestHeader(COOKIE) String cookie) {
		LOG.info("Getting shopping cart for user {}", cookie);
		return commerceItemService.mountShoppingCart(cookie);
	}

	@RequestMapping(value = "/items/{id}", method = DELETE)
	public void deleteItem(@RequestHeader(COOKIE) String cookie,
			@PathVariable("id") String item) {
		LOG.info("Deleting item {} from shopping cart {}", item, cookie);
		commerceItemService.remove(item, cookie);
	}

	@RequestMapping(value = "/items", method = POST)
	public void addItem(@RequestHeader(COOKIE) String cookie,
			@RequestParam("product_id") String product,
			@RequestParam("quantity") Integer quantity) {
		LOG.info("Putting {} item(s) of {} in shopping cart {}", quantity, product, cookie);
		commerceItemService.save(product, quantity, cookie);
	}

}
