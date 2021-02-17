#!/bin/bash
docker-compose up -d  --remove-orphans
./04-log-daemon.sh
