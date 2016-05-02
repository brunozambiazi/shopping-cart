package com.brunozambiazi.shopping.cart.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> extends Serializable {

	T getId();

	void setId(T id);

}
