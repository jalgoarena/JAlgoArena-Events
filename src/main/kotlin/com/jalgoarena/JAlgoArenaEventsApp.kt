package com.jalgoarena

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
@EnableDiscoveryClient
open class JAlgoArenaEventsApp

fun main(args: Array<String>) {
    SpringApplication.run(JAlgoArenaEventsApp::class.java, *args)
}