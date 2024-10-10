package com.coffee.ordering.system.connectors.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderApprovalResponseEventModel {
    private String id;
    private String sagaId;
    private String coffeeShopId;
    private String orderId;
    private LocalDateTime createdAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessages;
}
