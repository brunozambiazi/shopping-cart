package com.brunozambiazi.shopping.cart.entity;

import com.brunozambiazi.shopping.cart.transformer.EntityToIdSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CommerceItem extends GenericBaseEntity<String> implements BaseEntity<String> {

	private static final long serialVersionUID = 1370932017247461566L;

	@Id @Column
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("product_id") @JsonSerialize(using = EntityToIdSerializer.class)
	private Product product;

	@Column
	private Integer quantity;

	@Column
	private BigDecimal amount;

	@Column
	@JsonIgnore
	private String user;

	public CommerceItem() {
	}

	public CommerceItem(String id) {
		setId(id);
	}

}
