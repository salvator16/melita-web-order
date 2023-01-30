package com.melita.weborder.controller;

import com.melita.weborder.domain.entities.OrderDetail;
import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.service.OrderFulfillmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/fulfillment-order")
public class OrderFulfillmentController {

    private final OrderFulfillmentService orderFulfillmentService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<OrderDetail> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
	return ResponseEntity.status(HttpStatus.CREATED).body(orderFulfillmentService.applyOrder(orderRequestDto));
    }

}
