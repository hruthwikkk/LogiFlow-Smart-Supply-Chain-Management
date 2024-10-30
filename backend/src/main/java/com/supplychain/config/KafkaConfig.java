package com.supplychain.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    
    @Bean
    public NewTopic shipmentsTopic() {
        return TopicBuilder.name("shipments")
            .partitions(3)
            .replicas(1)
            .build();
    }
    
    @Bean
    public NewTopic inventoryTopic() {
        return TopicBuilder.name("inventory")
            .partitions(3)
            .replicas(1)
            .build();
    }
    
    @Bean
    public NewTopic alertsTopic() {
        return TopicBuilder.name("alerts")
            .partitions(3)
            .replicas(1)
            .build();
    }
}