package com.brunozambiazi.shopping.cart.entity;

import java.io.Serializable;
import java.util.Objects;

abstract class GenericBaseEntity<T extends Serializable> implements BaseEntity<T> {

	private static final long serialVersionUID = 3795428971766618699L;

	@Override
	public int hashCode() {
		return getId() == null ? super.hashCode() : getId().hashCode();
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BaseEntity<?>)) {
            return false;
        }

        BaseEntity<?> other = (BaseEntity<?>) obj;
        if (getId() == null && other.getId() == null) {
            return super.equals(obj);
        }

        return Objects.equals(getId(), other.getId());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + getId() + "]";
    }

}
