package com.coffee.ordering.system.connectors.kafka.consumer;

public interface KafkaConsumer<T> {
    void consume(T messages, String keys);
}
