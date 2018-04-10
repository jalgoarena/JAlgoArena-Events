#!/bin/bash
BOOTSTRAP_SERVERS="localhost:9092,localhost:9093,localhost:9094" PORT=5005 nohup java -jar jalgoarena-events-*.jar &
