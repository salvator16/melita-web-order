package com.melita.weborder.controller;

import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.model.OrderResponseDto;
import com.melita.weborder.service.OrderTakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/order")
public class OrderTakingController {

    private final OrderTakingService orderTakingService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
	return ResponseEntity.status(HttpStatus.CREATED).body(orderTakingService.createOrderEvent(orderRequestDto));
    }

}
