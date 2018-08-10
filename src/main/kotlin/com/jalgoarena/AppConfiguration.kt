package com.jalgoarena

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.kafka.listener.BatchLoggingErrorHandler
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean


@Configuration
@EnableWebSocketMessageBroker
open class AppConfiguration : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/events-websocket")
                .setAllowedOrigins("*")
                .withSockJS()
    }

    @Value("\${spring.kafka.bootstrap-servers:localhost:9092,localhost:9093,localhost:9094}")
    private lateinit var bootstrapServers: String

    @Value("\${spring.kafka.consumer.group-id:events}")
    private lateinit var groupId: String

    @Bean
    open fun consumerConfigs() =
            mapOf(
                    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                    KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                    VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                    GROUP_ID_CONFIG to groupId,
                    MAX_POLL_RECORDS_CONFIG to "10000",
                    ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG to "1000"
            )

    @Bean
    open fun consumerFactory(): ConsumerFactory<String, String> =
            DefaultKafkaConsumerFactory(consumerConfigs())

    @Bean
    open fun kafkaListenerContainerFactory() =
            ConcurrentKafkaListenerContainerFactory<String, String>().apply {
                consumerFactory = consumerFactory()
                isBatchListener = true
                containerProperties.setBatchErrorHandler(BatchLoggingErrorHandler())
            }
}