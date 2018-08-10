package com.jalgoarena

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jalgoarena.GenericEvent.Companion.REFRESH_RANKING_EVENT
import com.jalgoarena.GenericEvent.Companion.REFRESH_SUBMISSIONS_EVENT
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
    fun handleEvent(events: List<String>) {
        val genericEvents = transformToEvents(events)

        genericEvents[REFRESH_SUBMISSIONS_EVENT]?.forEach {
            logger.info("Publishing {} event", it)
            messagingTemplate.convertAndSend("/topic/events", OBJECT_MAPPER.writeValueAsString(it))
        }

        genericEvents[REFRESH_RANKING_EVENT]?.run {
            logger.info("Publishing {} event", REFRESH_RANKING_EVENT)
            messagingTemplate.convertAndSend("/topic/events", REFRESH_RANKING_EVENT)
        }
    }

    private fun transformToEvents(events: List<String>): Map<String, List<GenericEvent>> {
        return events.map { OBJECT_MAPPER.readValue<GenericEvent>(it) }
                .groupBy { it.type }
    }

    companion object {
        private val OBJECT_MAPPER = jacksonObjectMapper()
        private val REFRESH_RANKING_EVENT = """{
    "type": "${GenericEvent.REFRESH_RANKING_EVENT}"
}""".trimIndent()
    }
}