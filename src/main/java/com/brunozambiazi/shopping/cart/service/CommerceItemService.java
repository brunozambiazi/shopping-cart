package com.brunozambiazi.shopping.cart.service;

import com.brunozambiazi.shopping.cart.dto.ShoppingCart;
import com.brunozambiazi.shopping.cart.entity.CommerceItem;
import com.brunozambiazi.shopping.cart.exception.InvalidCommerceItemException;
import com.brunozambiazi.shopping.cart.exception.InvalidProductException;
import com.brunozambiazi.shopping.cart.exception.InvalidUserSessionException;

public interface CommerceItemService extends BaseService<CommerceItem, String> {

	/**
	 * Mount a shopping cart for the informed user.
	 *
	 * @param user The user for whom the shopping cart belongs.
	 * @return An instance of {@link ShoppingCart}.
	 */
	ShoppingCart mountShoppingCart(String user);

	/**
	 * Remove the informed commerce item if its really exists in repository and belongs to the informed user.
	 *
	 * @param id The id of the commerce item to be removed.
	 * @param user The user to which the item belongs to.
	 * @throws InvalidCommerceItemException If the informed item is invalid or not exists.
	 * @throws InvalidUserSessionException If the informed item doesn't belong to the informed user.
	 */
	void remove(String id, String user) throws InvalidCommerceItemException, InvalidUserSessionException;

	/**
	 * Save the quantity of some product as a commerce item of the user.
	 *
	 * @param productId The id of the product to be saved as an item.
	 * @param quantity The quantity of the product.
	 * @param user The user to which the item belongs to.
	 * @throws InvalidProductException If the product doesn't exist (or if the id is invalid)
	 *     or if the quantity is less than one.
	 */
	CommerceItem save(String productId, Integer quantity, String user) throws InvalidProductException;

}
