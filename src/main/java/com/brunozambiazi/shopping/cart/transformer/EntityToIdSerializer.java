package com.brunozambiazi.shopping.cart.transformer;

import com.brunozambiazi.shopping.cart.entity.BaseEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class EntityToIdSerializer extends JsonSerializer<BaseEntity<?>> {

	@Override
	public void serialize(BaseEntity<?> value, JsonGenerator json, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (value != null && value.getId() != null) {
			json.writeString(value.getId().toString());
		}
	}

}
