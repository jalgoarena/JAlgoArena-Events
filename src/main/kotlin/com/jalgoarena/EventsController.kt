package com.jalgoarena

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class EventsController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var messagingTemplate: SimpMessagingTemplate

    @KafkaListener(topics = ["events"])
    fun handleEvent(event: String) {
        logger.info("Publishing {} event", event)
        messagingTemplate.convertAndSend("/topic/events", event)
    }
}