package com.coffee.ordering.system.connectors.kafka.producer;

import com.coffee.ordering.system.connectors.outbox.OutboxStatus;
import org.springframework.kafka.support.SendResult;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface KafkaProducer<K extends Serializable, V> {
    void publish(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback);

    <T, U> BiConsumer<SendResult<String, T>, Throwable> callback(String topicName, U outboxMessage,
                                                                 BiConsumer<U, OutboxStatus> outboxCallback,
                                                                 String orderId);
}
