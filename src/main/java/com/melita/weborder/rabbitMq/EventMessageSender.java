package com.melita.weborder.rabbitMq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMessageSender {

    private final RabbitTemplate rabbitTemplate;

    public <T> void sendEvent (String exchange, String routingKey, T message) {
	rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
