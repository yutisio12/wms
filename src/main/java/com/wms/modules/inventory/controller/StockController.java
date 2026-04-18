package com.wms.modules.inventory.controller;

import com.wms.modules.inventory.service.StockService;
import com.wms.modules.inventory.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
  private final StockService service;
  private final ReservationService reservService;

  @GetMapping("/health")
  public String health() {
    return "Good";
  }

  @PostMapping("/add")
  // @GetMapping("/add")
  public String add() {
    service.addStock(
        UUID.randomUUID(),
        UUID.randomUUID(),
        BigDecimal.valueOf(100));
    return "Stock added successfully";
  }

  @GetMapping("/available/{productId}")
  public BigDecimal available(@PathVariable("productId") UUID productId) {
    return service.getAvailableStock(productId);
  }

  @PostMapping("/reserve/{productId}")
  public String reserve(@PathVariable("productId") UUID productId) {
    reservService.reserve(productId, new BigDecimal("10"));
    return "RESERVED";
  }
}
