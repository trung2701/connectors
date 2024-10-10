package com.coffee.ordering.system.connectors.kafka.consumer;

import com.coffee.ordering.system.connectors.kafka.configuration.ConsumerProperties;
import com.coffee.ordering.system.connectors.kafka.configuration.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaProperties.class, ConsumerProperties.class})
public class ConsumerConfiguration<K extends Serializable, V> {
    private final KafkaProperties kafkaProperties;
    private final ConsumerProperties consumerProperties;

    @Primary
    @Bean(name = "jsonConsumerFactory")
    public ConsumerFactory<K, V> jsonConsumerFactory() {
        Map<String, Object> consumerConfiguration = new HashMap<>();
        consumerConfiguration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        consumerConfiguration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerProperties.getKeyDeserializer());
        consumerConfiguration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerConfiguration.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(consumerConfiguration);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> jsonKafkaListenerContainerFactory(ConsumerFactory<K, V> jsonConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonConsumerFactory);
        return factory;
    }

    @Bean(name = "stringConsumerFactory")
    public ConsumerFactory<K, String> stringConsumerFactory() {
        Map<String, Object> consumerConfiguration = new HashMap<>();
        consumerConfiguration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        consumerConfiguration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerProperties.getKeyDeserializer());
        consumerConfiguration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfiguration.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(consumerConfiguration);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, String>> stringKafkaListenerContainerFactory(ConsumerFactory<K, String> stringConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<K, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory);
        return factory;
    }
}
