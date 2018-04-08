package com.jalgoarena

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
open class JAlgoArenaEventsApp

fun main(args: Array<String>) {
    SpringApplication.run(JAlgoArenaEventsApp::class.java, *args)
}