package com.melita.weborder.client;

import com.melita.weborder.config.feign.ExternalServiceFeignConfiguration;
import com.melita.weborder.model.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
		value = "order-fulfillment",
		url = "#{'${melita.client.fulfillment-url}'}",
		configuration = ExternalServiceFeignConfiguration.class
)
public interface OrderFulfillmentClient {

    @PostMapping(
		    value = "/api/fulfillment-order")
    ResponseEntity sendOrder(@RequestBody OrderRequestDto orderRequestDto);

}
