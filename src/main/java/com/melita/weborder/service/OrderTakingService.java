package com.melita.weborder.service;

import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.model.OrderResponseDto;
import com.melita.weborder.rabbitMq.EventMessageSender;
import com.melita.weborder.rabbitMq.QueueProperties;
import com.melita.weborder.validator.OrderReqValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderTakingService {

    private final OrderReqValidator orderValidator;
    private final EventMessageSender messageSender;
    private final QueueProperties queueProperties;

    public OrderResponseDto createOrderEvent(OrderRequestDto orderMessage) {
	orderValidator.validateOrderRequest(orderMessage);
	messageSender.sendEvent(queueProperties.getOrderExchange(), queueProperties.getOrderRoutingKey(), orderMessage);
	return new OrderResponseDto(orderMessage.getOrderId());
    }

}
