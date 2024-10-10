package com.coffee.ordering.system.connectors.kafka.exception;

public class KafkaException extends RuntimeException {

    public KafkaException(String message) {
        super(message);
    }
}
