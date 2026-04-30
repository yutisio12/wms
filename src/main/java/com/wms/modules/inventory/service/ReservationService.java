package com.wms.modules.inventory.service;

import com.wms.modules.inventory.domain.StockReservation;
import com.wms.modules.inventory.repository.StockQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
  private final EntityManager em;
  private final StockQueryRepository queryRepo;

  @Transactional
  public void reserve(UUID productId, BigDecimal qty) {
    BigDecimal stock = em.createQuery("""
        SELECT COALESCE(SUM(sm.quantity), 0)
        FROM StockMovement sm
        WHERE sm.productId = :productId
        """, BigDecimal.class)
        .setParameter("productId", productId)
        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
        .getSingleResult();

    BigDecimal reserved = queryRepo.getReserved(productId);
    BigDecimal available = stock.subtract(reserved);

    if (available.compareTo(qty) < 0) {
      throw new RuntimeException("Stock isnt Available");
    }

    StockReservation r = new StockReservation();
    r.setId(UUID.randomUUID());
    r.setProductId(productId);
    r.setReserveQty(qty);
    r.setStatus("RESERVED");
    r.setCreatedAt(LocalDateTime.now());

    em.persist(r);
  }

}
