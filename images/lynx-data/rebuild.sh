#!/usr/bin/env bash
docker rm -f lynxdb
docker rmi gatblau/lynx-data
sh build.sh
sh run.sh