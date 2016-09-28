#!/usr/bin/env bash
echo '--> Removing existing container...'
docker rm -f lynxdb
echo '--> Removing existing image...'
docker rmi gatblau/lynx-data
echo '--> Rebuilding image...'
sh build.sh
echo '--> Launching container...'
sh run.sh