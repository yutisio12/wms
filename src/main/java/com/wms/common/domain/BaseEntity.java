package com.wms.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
  @Id
  private UUID id;

  private LocalDateTime createdAt;
  private LocalDateTime createdDate;
}
