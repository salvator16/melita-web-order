package com.melita.weborder.validator;

import com.melita.weborder.exception.ProductNotFoundException;
import com.melita.weborder.exception.RequestNotValidException;
import com.melita.weborder.model.CustomerDetails;
import com.melita.weborder.model.OrderRequestDto;
import com.melita.weborder.model.ProductDto;
import com.melita.weborder.model.enums.PackageType;
import com.melita.weborder.model.enums.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.melita.weborder.model.enums.ProductType.*;
import static com.melita.weborder.model.enums.PackageType.*;

@Slf4j
@Component
public class OrderReqValidator {

    //Validations can be enhanced

    public void validateOrderRequest(OrderRequestDto orderRequestDto) {
	verifyCustomerDetails(orderRequestDto.getCustomerDetails());
	verifyProduct(orderRequestDto.getProducts());
    }

    private void verifyProduct(List<ProductDto> products) {
	if (CollectionUtils.isEmpty(products))
	    throw new RequestNotValidException("Product can not be empty");

	products.forEach(product -> {
	    switch (product.getProductType()) {
	    case Internet, Mobile, Telephony, Tv -> checkPackageType(product.getProductType(), product.getPackageType());
	    default -> throw new ProductNotFoundException("Unknown product type");
	    }
	});

    }

    private void checkPackageType(ProductType type, PackageType packageType) {
	if (type == Internet && ((packageType != Internet_250Mbps) && (packageType != Internet_1Gbps)))
	    throw new ProductNotFoundException("Package mismatched for Internet");
	else if (type == Tv && ((packageType != Tv_90) && (packageType != Tv_140)))
	    throw new ProductNotFoundException("Package mismatched for Television");
	else if (type == Telephony && ((packageType != Telephony_On_Net) && (packageType != Telephony_Unlimited)))
	    throw new ProductNotFoundException("Package mismatched for Telephony");
	else if (type == Mobile && ((packageType != Mobile_Prepaid) && (packageType != Mobile_Postpaid)))
	    throw new ProductNotFoundException("Package mismatched for Mobile");

    }

    private void verifyCustomerDetails(CustomerDetails details) {
	if (details == null || ObjectUtils.isEmpty(details.getEmail())
			|| ObjectUtils.isEmpty(details.getIdentityNumber()) ||  ObjectUtils.isEmpty(details.getPhoneNumber())
			|| ObjectUtils.isEmpty(details.getName()) ||  ObjectUtils.isEmpty(details.getInstallationAddress())) {
	    log.error("Customer information is missing");
	    throw new RequestNotValidException("Customer information not satisfied");
	}
    }

}
