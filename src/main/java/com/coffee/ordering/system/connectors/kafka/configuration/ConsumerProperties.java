package com.coffee.ordering.system.connectors.kafka.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class ConsumerProperties {
    private String keyDeserializer;
}
