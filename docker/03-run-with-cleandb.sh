#!/bin/bash
echo "ted smazu container"
docker container rm edu-db 
echo "ted smazu volume"
docker volume rm docker_edu-db
echo "ted poustim 03-run.sh"
#read
./03-run.sh
