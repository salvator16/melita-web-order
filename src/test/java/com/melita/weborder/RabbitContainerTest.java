package com.melita.weborder;

import com.melita.weborder.model.CustomerDetails;
import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.model.ProductDto;
import com.melita.weborder.model.enums.PackageType;
import com.melita.weborder.model.enums.ProductType;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rnorth.ducttape.unreliables.Unreliables;
import org.springframework.util.SerializationUtils;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;

@Testcontainers
public class RabbitContainerTest {

    private static final String RABBITMQ_TEST_EXCHANGE = "order_web";
    private static final String RABBITMQ_TEST_ROUTING_KEY = "order.web";
    private static final int RABBITMQ_PORT = 5672;

    @Container
    public static RabbitMQContainer rabbitMQContainer =
		    new RabbitMQContainer("rabbitmq:3-management-alpine");


    private OrderRequestDto message;

    @BeforeEach
    void setUp() throws ParseException {
	ProductDto productDto = new ProductDto();
	productDto.setProductType(ProductType.Internet);
	productDto.setPackageType(PackageType.Internet_1Gbps);
	message = new OrderRequestDto();
	message.setOrderId(123);
	message.setCustomerDetails(CustomerDetails.builder()
			.email("melita@melita.com")
			.name("Abc")
			.surname("Efg")
			.address("New York LA")
			.build());
	message.setProducts(List.of(productDto));
    }

    @Test
    void check_RabbitMq_Container_Is_Running() {
	assertTrue(rabbitMQContainer.isRunning());
    }

    @Test
    public void simpleRabbitMqTest() throws IOException, TimeoutException {
	ConnectionFactory factory = new ConnectionFactory();
	factory.setHost(rabbitMQContainer.getHost());
	factory.setPort(rabbitMQContainer.getMappedPort(RABBITMQ_PORT));
	Connection connection = factory.newConnection();

	Channel channel = connection.createChannel();
	channel.exchangeDeclare(RABBITMQ_TEST_EXCHANGE, "topic", true);
	String queueName = channel.queueDeclare().getQueue();
	channel.queueBind(queueName, RABBITMQ_TEST_EXCHANGE, RABBITMQ_TEST_ROUTING_KEY);

	final boolean[] messageWasReceived = new boolean[1];
	channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
	    @Override
	    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		messageWasReceived[0] = Arrays.equals(body, convertObjToByteArray(message));
	    }
	});

	channel.basicPublish(RABBITMQ_TEST_EXCHANGE, RABBITMQ_TEST_ROUTING_KEY, null, convertObjToByteArray(message));

	assertTrue("The message was received", Unreliables.retryUntilSuccess(5, TimeUnit.SECONDS, () -> {
	    if (!messageWasReceived[0]) {
		throw new IllegalStateException("Message not received yet");
	    }
	    return true;
	}));
    }

    private byte[] convertObjToByteArray(Object obj) {
	return SerializationUtils.serialize(obj);
    }

}

