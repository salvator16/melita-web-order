package com.melita.weborder.domain.entities;

import com.melita.weborder.model.enums.PackageType;
import com.melita.weborder.model.enums.ProductType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class OrderProductInfo extends SoftDeletableEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private int order_id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderDetail orderDetail;

}
