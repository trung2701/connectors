package com.coffee.ordering.system.connectors.kafka.producer;

import com.coffee.ordering.system.connectors.kafka.configuration.KafkaProperties;
import com.coffee.ordering.system.connectors.kafka.configuration.ProducerProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.Serializable;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaProperties.class, ProducerProperties.class})
public class KafkaProducerConfiguration<K extends Serializable, V> {
    private final KafkaProperties kafkaProperties;
    private final ProducerProperties producerProperties;

    @Bean
    public KafkaTemplate<K, V> kafkaTemplate() {
        Map<String, Object> producerConfiguration = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers(),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerProperties.getKeySerializerClass(),
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerProperties.getValueSerializerClass()
        );

        ProducerFactory<K, V> producerFactory = new DefaultKafkaProducerFactory<>(producerConfiguration);
        return new KafkaTemplate<>(producerFactory);
    }
}
