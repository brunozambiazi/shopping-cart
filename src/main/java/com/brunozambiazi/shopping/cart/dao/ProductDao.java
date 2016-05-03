package com.brunozambiazi.shopping.cart.dao;

import com.brunozambiazi.shopping.cart.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, String> {

}
