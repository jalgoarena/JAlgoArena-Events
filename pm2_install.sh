#!/usr/bin/env bash
pm2 stop events
pm2 delete events
./gradlew clean
./gradlew stage
pm2 start pm2.config.js