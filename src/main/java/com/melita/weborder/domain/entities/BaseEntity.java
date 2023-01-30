package com.melita.weborder.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Getter
    @Setter
    @CreatedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    @Version
    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer version;

    public abstract Object getId();
}

