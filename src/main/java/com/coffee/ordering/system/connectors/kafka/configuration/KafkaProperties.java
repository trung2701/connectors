package com.coffee.ordering.system.connectors.kafka.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaProperties {
    private String bootstrapServers;
}
