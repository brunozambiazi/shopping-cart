package com.brunozambiazi.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product extends GenericBaseEntity<String> implements BaseEntity<String> {

	private static final long serialVersionUID = -1179288264724332167L;

	@Id @Column
	private String id;

	@Column
	private String name;

	@Column
	@JsonProperty("image")
	private String imageUrl;

	@Column
	private BigDecimal price;

	public Product() {
	}

	public Product(String id) {
		setId(id);
	}

}
