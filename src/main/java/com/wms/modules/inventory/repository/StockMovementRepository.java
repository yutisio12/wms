package com.wms.modules.inventory.repository;

import com.wms.modules.inventory.domain.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {
}