package com.melita.weborder.rabbitMq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "amqp.config")
public class QueueProperties {

    private String orderExchange;
    private String orderQueue;
    private String orderRoutingKey;

}
