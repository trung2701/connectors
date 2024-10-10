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
public class PaymentRequestEventModel {
    private String id;
    private String sagaId;
    private String paymentId;
    private String customerId;
    private String orderId;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private PaymentStatus paymentStatus;
    private List<String> failureMessages;
}
