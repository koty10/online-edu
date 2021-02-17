#!/bin/bash
docker exec -t edu-db pg_dumpall -c -U edu | gzip > ../dump_`date +%Y-%m-%d"_"%H_%M_%S`.sql.gz
