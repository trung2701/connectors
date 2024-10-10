package com.coffee.ordering.system.connectors.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderApprovalRequestEventModel {
    private String id;
    private String sagaId;
    private String coffeeShopId;
    private String orderId;
    private CoffeeShopOrderStatus coffeeShopOrderStatus;
    private List<Product> products;
    private BigDecimal price;
    private LocalDateTime createdAt;
}
