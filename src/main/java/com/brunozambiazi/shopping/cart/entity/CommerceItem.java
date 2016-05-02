package com.brunozambiazi.shopping.cart.entity;

import com.brunozambiazi.shopping.cart.transformer.EntityToIdSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommerceItem extends GenericBaseEntity<String> implements BaseEntity<String> {

	private static final long serialVersionUID = 1370932017247461566L;

	private String id;

	@JsonProperty("product_id")
	@JsonSerialize(using = EntityToIdSerializer.class)
	private Product product;

	private Integer quantity;

	private BigDecimal amount;

	@JsonIgnore
	private String user;

	public CommerceItem() {
	}

	public CommerceItem(String id) {
		setId(id);
	}

}
