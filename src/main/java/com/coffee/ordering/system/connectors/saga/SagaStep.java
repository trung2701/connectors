package com.coffee.ordering.system.connectors.saga;

public interface SagaStep<T> {
    void process(T data);

    void rollback(T data);
}
