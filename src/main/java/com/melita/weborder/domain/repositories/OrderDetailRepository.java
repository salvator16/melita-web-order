package com.melita.weborder.domain.repositories;

import com.melita.weborder.domain.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
