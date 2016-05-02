package com.brunozambiazi.shopping.cart.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.brunozambiazi.shopping.cart.entity.Product;
import com.brunozambiazi.shopping.cart.service.ProductService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/products", produces = APPLICATION_JSON_VALUE)
public class ProductsController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsController.class);

	//@Autowired
	private ProductService prouctService;

	@RequestMapping(method = GET)
	public Collection<Product> getProducts() {
		LOG.info("Getting all products");
		return prouctService.findAll();
	}

}
