package com.melita.weborder.service.listener;

import com.melita.weborder.client.OrderFulfillmentClient;
import com.melita.weborder.model.EmailDto;
import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.service.notification.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTakingListener {

    private final EmailService emailService;

    private final OrderFulfillmentClient orderFulfillmentClient;

    @RabbitListener(
		    id = "OrderConsumer",
		    queues = "#{orderQueue.name}",
		    concurrency = "1"
    )
    public void consumeOrderEvent(OrderRequestDto message) {
	if (message != null && message.getOrderId() != 0) {
	    ResponseEntity response = orderFulfillmentClient.sendOrder(message);
	    if (response.getStatusCode() == HttpStatus.CREATED) {
		emailService.sendBasicMail(new EmailDto(message.getCustomerDetails().getEmail(), "Body with detail", "Order completed"));
		log.info("Order with id successfully sent to the fulfillment service {}", message.getOrderId());
	    }
	} else {
	    log.error("Order creation FAILED !!");
	}
    }

}
