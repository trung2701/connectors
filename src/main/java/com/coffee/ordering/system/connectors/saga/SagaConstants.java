package com.coffee.ordering.system.connectors.saga;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SagaConstants {

    public static final String ORDER_SAGA_NAME = "OrderProcessingSaga";
}
