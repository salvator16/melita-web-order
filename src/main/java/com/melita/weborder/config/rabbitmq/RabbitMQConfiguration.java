package com.melita.weborder.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.melita.weborder.rabbitMq.QueueProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableRabbit
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {

    private final QueueProperties queueProperties;

    /**
     * Order Related Definitions
     */

    @Bean("orderExchange")
    TopicExchange orderExchange() {
        return ExchangeBuilder
                        .topicExchange(queueProperties.getOrderExchange())
                        .build();
    }

    @Bean("orderQueue")
    Queue orderQueue() {
        return QueueBuilder
                        .durable(queueProperties.getOrderQueue())
                        .build();
    }

    @Bean
    Binding bindingOrderMessages(@Qualifier("orderQueue") Queue orderQueue,
                    @Qualifier("orderExchange") TopicExchange orderExchange) {
        return BindingBuilder
                        .bind(orderQueue)
                        .to(orderExchange)
                        .with(queueProperties.getOrderRoutingKey());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                    ObjectMapper objectMapper) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter(objectMapper));

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
