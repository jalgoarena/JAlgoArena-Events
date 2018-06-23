# JAlgoArena Events [![Build Status](https://travis-ci.org/spolnik/JAlgoArena-Events.svg?branch=master)](https://travis-ci.org/spolnik/JAlgoArena-Events) [![codecov](https://codecov.io/gh/spolnik/JAlgoArena-Events/branch/master/graph/badge.svg)](https://codecov.io/gh/spolnik/JAlgoArena-Events) [![GitHub release](https://img.shields.io/github/release/spolnik/jalgoarena-events.svg)]()

JAlgoArena Events microservice to publish Events via WebSocket STOMP messages

Events:
* refresh ranking
* refresh user submissions

- [Introduction](#introduction)
- [WS API](#ws-api)
- [Components](#components)
- [Continuous Delivery](#continuous-delivery)
- [Infrastructure](#infrastructure)
- [Running Locally](#running-locally)
- [Notes](#notes)

## Introduction

- JAlgoArena Events is responsible for collecting from Kafka events and publishing them via via WebSocket API

![Component Diagram](https://github.com/spolnik/JAlgoArena-Events/raw/master/design/component_diagram.png)

# WS API

| Endpoint | Description |
| ---- | --------------- |
| WS/SockJS [/events-websocket] | WebSocket endpoint, uses SockJS/WS |
| [/topic/events] | Topic to subscribe for events |

## Components

- [JAlgoArena](https://github.com/spolnik/JAlgoArena)
- [JAlgoArena UI](https://github.com/spolnik/JAlgoArena-UI)

## Continuous Delivery

- initially, developer push his changes to GitHub
- in next stage, GitHub notifies Travis CI about changes
- Travis CI runs whole continuous integration flow, running compilation, tests and generating reports
- coverage report is sent to Codecov

## Infrastructure

- Spring Boot, WebSockets
- TravisCI - https://travis-ci.org/spolnik/JAlgoArena-Events
- Apache Kafka

## Running locally

There are two ways to run it - from sources or from binaries.
- default port: `5005`

### Running from binaries
- go to [releases page](https://github.com/spolnik/JAlgoArena-Events/releases) and download last app package (JAlgoArena-Events-[version_number].zip)
- after unpacking it, go to folder and run `./run.sh` (to make it runnable, invoke command `chmod +x run.sh`)
- you can modify port in run.sh script, depending on your infrastructure settings. The script itself can be found in here: [run.sh](run.sh)

### Running from sources
- run `git clone https://github.com/spolnik/JAlgoArena-Events` to clone locally the sources
- now, you can build project with command `./gradlew clean stage` which will create runnable jar package with app sources. Next, run `java -jar build/libs/jalgoarena-events-*.jar` which will start application
- there is second way to run app with Gradle. Instead of running above, you can just run `./gradlew clean bootRun`

## Notes
- [Travis Builds](https://travis-ci.org/spolnik)

![Component Diagram](https://github.com/spolnik/JAlgoArena/raw/master/design/JAlgoArena_Logo.png)
