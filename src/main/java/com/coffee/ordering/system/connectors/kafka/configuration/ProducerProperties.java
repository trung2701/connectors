package com.coffee.ordering.system.connectors.kafka.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kafka-producer-config")
public class ProducerProperties {
    private String keySerializerClass;
    private String valueSerializerClass;
}
