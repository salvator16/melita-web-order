package com.melita.weborder.domain.repositories;

import com.melita.weborder.domain.entities.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderUserRepository extends JpaRepository<OrderUser, UUID> {

    Optional<OrderUser> findByEmailAndPassword(String email, String password);

}
