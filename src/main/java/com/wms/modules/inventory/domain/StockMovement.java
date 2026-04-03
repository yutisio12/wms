package com.wms.modules.inventory.domain;

import com.wms.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
public class StockMovement extends BaseEntity {

  private UUID productId;
  private UUID warehouseId;
  private UUID locationId;

  private BigDecimal quantity;

  private String movementType;
  private String referenceType;
  private UUID referenceId;

}
