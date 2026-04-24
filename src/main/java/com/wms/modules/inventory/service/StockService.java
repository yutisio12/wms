package com.wms.modules.inventory.service;

import com.wms.modules.inventory.domain.StockMovement;
import com.wms.modules.inventory.repository.StockMovementRepository;
import com.wms.modules.inventory.repository.StockQueryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockService {

  private final StockMovementRepository repo;
  private final StockQueryRepository queryRepo;

  @Transactional
  public void addStock(UUID productId, UUID warehouseId, BigDecimal qty) {
    StockMovement sm = new StockMovement();
    sm.setId(UUID.randomUUID());
    sm.setProductId(productId);
    sm.setWarehouseId(warehouseId);
    sm.setQuantity(qty);
    sm.setMovementType("IN");
    sm.setCreatedAt(LocalDateTime.now());

    repo.save(sm);
  }

  public BigDecimal getAvailableStock(UUID productId) {
    BigDecimal stock = queryRepo.getStock(productId);
    BigDecimal reserved = queryRepo.getReserved(productId);
    return stock.subtract(reserved);
  }

}