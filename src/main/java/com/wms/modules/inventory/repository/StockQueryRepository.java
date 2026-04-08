package com.wms.modules.inventory.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.wms.modules.inventory.domain.StockMovement;

import java.math.BigDecimal;
import java.util.UUID;

public interface StockQueryRepository extends Repository<StockMovement, UUID> {

  @Query("""
        SELECT
          coalesce(sum(sm.quantity), 0)
        FROM StockMovement sm
        WHERE
          sm.productId = :productId
      """)
  BigDecimal getStock(@Param("productId") UUID productId);

  @Query("""
        SELECT
          coalesce(sum(sr.reserveQty), 0)
        FROM StockReservation sr
        WHERE
          sr.productId = :productId
          AND sr.status = 'RESERVED'
      """)
  BigDecimal getReserved(@Param("productId") UUID productId);
}
