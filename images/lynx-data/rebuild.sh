#!/usr/bin/env bash
echo '--> Removing existing container...'
docker rm -f lynxdb
echo '--> Removing exisitng image...'
docker rmi gatblau/lynx-data
echo '--> Rebuilding image...'
sh build.sh
echo '--> Launching container...'
sh run.sh