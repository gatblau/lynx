#!/usr/bin/env bash
docker run --name lynxdb -e MYSQL_ROOT_PASSWORD=Passw0rd! -e MYSQL_PASSWORD=L1nX -p 3306:3306 -d gatblau/lynx-data:latest
