package com.melita.weborder.service;

import com.melita.weborder.converter.OrderDetailConverter;
import com.melita.weborder.domain.entities.OrderDetail;
import com.melita.weborder.domain.repositories.OrderDetailRepository;
import com.melita.weborder.exception.RequestNotValidException;
import com.melita.weborder.model.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailConverter orderDetailConverter;

    public OrderDetail applyOrder(OrderRequestDto orderRequestDto) {
        //Apply if there is any fulfillment specific validation then save.
        if (orderRequestDto == null)
            throw new RequestNotValidException("request can not be empty");
        return orderDetailRepository.save(Objects.requireNonNull(orderDetailConverter.convert(orderRequestDto)));
    }

}
