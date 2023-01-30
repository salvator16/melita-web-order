package com.melita.weborder.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class SoftDeletableEntity extends BaseEntity {

    private LocalDateTime deletedAt;

    public boolean isDeleted() {
	return deletedAt != null;
    }
}

