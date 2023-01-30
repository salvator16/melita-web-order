package com.melita.weborder.converter;

import com.melita.weborder.domain.entities.OrderDetail;
import com.melita.weborder.domain.entities.OrderProductInfo;
import com.melita.weborder.model.OrderRequestDto;
import org.aspectj.weaver.ast.Or;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailConverter implements Converter<OrderRequestDto, OrderDetail> {

    @Override
    public OrderDetail convert(OrderRequestDto source) {
	OrderDetail orderDetail = new OrderDetail();

	orderDetail.setOrderId(source.getOrderId());
	orderDetail.setCustomerName(source.getCustomerDetails().getName());
	orderDetail.setCustomerLastName(source.getCustomerDetails().getSurname());
	orderDetail.setInstallationAddress(source.getCustomerDetails().getInstallationAddress());
	orderDetail.setInstallationDate(source.getCustomerDetails().getInstallationDate());
	orderDetail.setPhoneNumber(source.getCustomerDetails().getPhoneNumber());
	orderDetail.setIdentityNumber(source.getCustomerDetails().getIdentityNumber());

	List<OrderProductInfo> orderProductInfoList = source.getProducts()
			.stream()
			.map( productDto -> {
			    OrderProductInfo orderProductInfo = new OrderProductInfo();
			    orderProductInfo.setOrderDetail(orderDetail);
			    orderProductInfo.setOrder_id(source.getOrderId());
			    orderProductInfo.setProductType(productDto.getProductType());
			    orderProductInfo.setPackageType(productDto.getPackageType());
			    return  orderProductInfo;
			}).collect(Collectors.toList());

	if (!CollectionUtils.isEmpty(orderProductInfoList)) {
	    orderDetail.setProductInfos(orderProductInfoList);
	}

	return orderDetail;
    }
}
