#!/bin/bash
# payara - PG driver
mkdir -p edu-payara/lib
cd edu-payara/lib && curl -sO https://jdbc.postgresql.org/download/postgresql-42.2.8.jar && cd ../..
# payara - app
mkdir -p edu-payara/deployment
cd ../onlineedu && mvn clean package && cd ../docker
cp ../onlineedu/target/*.war edu-payara/deployment

