#!/usr/bin/env bash
#   -v /media/sf_files/lynx/images/db/docker-entrypoint-initdb.d:/docker-entrypoint-initdb.d \
#   -v /media/sf_files/lynx/images/db/conf.d:/etc/mysql/conf.d \
docker run --name lynxdb \
   -e MYSQL_ROOT_PASSWORD=Passw0rd! \
   -e MYSQL_USER=lynx \
   -e MYSQL_PASSWORD=l1nx \
   -p 3306:3306 \
   -d \
   mariadb:latest \
   --character-set-server=utf8 \
   --collation-server=utf8_unicode_ci

# docker run --name lynx-data -e MYSQL_ROOT_PASSWORD=Passw0rd! -e MYSQL_PASSWORD=l1nx -p 3307:3306 -d lynx-data:latest
# docker run -it lynx:latest