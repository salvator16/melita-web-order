package com.melita.weborder.service;

import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.rabbitMq.EventMessageSender;
import com.melita.weborder.rabbitMq.QueueProperties;
import com.melita.weborder.validator.OrderReqValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderTakingServiceTest {

    private OrderTakingService orderTakingService;

    @Mock
    private EventMessageSender messageSender;

    @Mock
    private QueueProperties queueProperties;

    @Mock
    private OrderReqValidator orderReqValidator;

    @Mock
    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void setUp() {
	orderTakingService = new OrderTakingService(orderReqValidator,messageSender,queueProperties);
    }

    @Test
    void when_OrderRequestValid_Then_SendEvent() {
	doNothing().when(orderReqValidator).validateOrderRequest(any());
        orderTakingService.createOrderEvent(orderRequestDto);
        verify(messageSender).sendEvent(any(), any(), any());
    }


}