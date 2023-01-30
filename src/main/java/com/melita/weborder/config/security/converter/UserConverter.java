package com.melita.weborder.config.security.converter;

import com.melita.weborder.domain.entities.OrderUser;
import com.melita.weborder.model.UserResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<OrderUser, UserResource> {

    @Override
    public UserResource convert(OrderUser entity) {
	return UserResource.builder()
			.email(entity.getEmail())
			.firstName(entity.getFirstName())
			.lastName(entity.getLastName())
			.build();
    }
}
