package com.melita.weborder.service.listener;

import com.melita.weborder.client.OrderFulfillmentClient;
import com.melita.weborder.model.CustomerDetails;
import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.service.notification.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderTakingListenerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private OrderFulfillmentClient orderFulfillmentClient;

    @Mock
    private OrderRequestDto orderRequestDto;

    private OrderTakingListener orderTakingListener;

    @BeforeEach
    void setUp() {
	orderTakingListener = new OrderTakingListener(emailService, orderFulfillmentClient);
    }

    @Test
    void verifyEventSuccessfully_Received_Then_EmailService_Invoked() {
	when(orderRequestDto.getOrderId()).thenReturn(123);
	when(orderRequestDto.getCustomerDetails()).thenReturn(getCustomerDetail());
	when(orderFulfillmentClient.sendOrder(any())).thenReturn(mockedOrderFulfillment());
	orderTakingListener.consumeOrderEvent(orderRequestDto);
	verify(emailService, times(1)).sendBasicMail(any());
    }

    private ResponseEntity mockedOrderFulfillment() {
	return ResponseEntity.status(201).body(null);
    }

    private CustomerDetails getCustomerDetail() {
	return CustomerDetails.builder()
			.email("melita@melita.com")
			.build();
    }
}
