package com.melita.weborder.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderRequestDto implements Serializable {

    private int orderId;
    private CustomerDetails customerDetails;
    private List<ProductDto> products;


}
