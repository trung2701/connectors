package com.coffee.ordering.system.connectors.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseEventModel {
    private String id;
    private String sagaId;
    private String paymentId;
    private String customerId;
    private String orderId;
    private BigDecimal price;
    private String createdAt;
    private PaymentStatus paymentStatus;
    private List<String> failureMessages;
}
