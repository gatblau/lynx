#!/usr/bin/env bash
function on_error() {
  echo "Error on line $1"
  exit 1
}
trap 'on_error $LINENO' ERR

DATADIR="/var/lib/mysql"

if [ ! -d "$DATADIR/mysql" ]; then
    if [ -z "$MYSQL_ROOT_PASSWORD" ]; then
        echo >&2 'error: MYSQL_ROOT_PASSWORD not set'
        echo >&2 '  Did you forget to add -e MYSQL_ROOT_PASSWORD=... ?'
        exit 1
    fi
    if [ -z "$MYSQL_PASSWORD" ]; then
        echo >&2 'error: MYSQL_PASSWORD not set'
        echo >&2 '  Did you forget to add -e MYSQL_PASSWORD=... ?'
        exit 1
    fi
    mysql_install_db --datadir="$DATADIR"

    initSqlFile='/config/mysql-init.sql'

    cat >> "$initSqlFile" <<-EOSQL
DELETE FROM mysql.user ;
CREATE USER 'root'@'%' IDENTIFIED BY '${MYSQL_ROOT_PASSWORD}' ;
GRANT ALL ON *.* TO 'root'@'%' WITH GRANT OPTION ;
DROP DATABASE IF EXISTS test ;
CREATE DATABASE IF NOT EXISTS 'lynxc' DEFAULT CHARACTER SET utf8 ;
CREATE USER 'lynx'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}' ;
GRANT ALL ON 'lynxc'.* TO 'lynx'@'%' ;
EOSQL

    cat '/config/tables.sql' >> "$initSqlFile"
fi

exec mysqld_safe
