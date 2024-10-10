package com.coffee.ordering.system.connectors.kafka.producer;

import com.coffee.ordering.system.connectors.kafka.exception.KafkaException;
import com.coffee.ordering.system.connectors.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V> implements KafkaProducer<K, V> {
    @Lazy
    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void publish(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.whenComplete(callback);
        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    public <T, U> BiConsumer<SendResult<String, T>, Throwable> callback(String topicName, U outboxMessage,
                                                                        BiConsumer<U, OutboxStatus> outboxCallback,
                                                                        String orderId) {
        return (result, ex) -> {
            if (ex == null) {
                log.info("Received successful response from Kafka for order id: {}", orderId);
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            } else {
                log.error("Error sending order id {} to Kafka with message: {} to topic {}", orderId, outboxMessage, topicName, ex);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }
}
