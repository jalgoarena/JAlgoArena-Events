package com.jalgoarena

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class EventsController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = ["events"])
    @SendTo("/topic/events")
    fun handleEvent(event: String): String {
        logger.info("Publishing {} event", event)
        return event
    }
}