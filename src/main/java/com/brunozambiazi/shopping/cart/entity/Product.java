package com.brunozambiazi.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product extends GenericBaseEntity<String> implements BaseEntity<String> {

	private static final long serialVersionUID = -1179288264724332167L;

	private String id;

	private String name;

	@JsonProperty("image")
	private String imageUrl;

	private BigDecimal price;

	public Product() {
	}

	public Product(String id) {
		setId(id);
	}

}
