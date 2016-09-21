#!/usr/bin/env bash

MySqlInitFile='/config/mysql-init.sql'

echo 'Checking the database server has been initialised'
if [ ! -e "$MySqlInitFile" ]; then
    echo 'The database server is not initialised.'
    echo 'Attempting to initialise the server.'
    echo 'Checking for required environment variables.'
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

    echo 'Creating mysql-init file.'
    echo 'Appending Users and Databases to mysql-init file'
    cat > "$MySqlInitFile" <<-EOSQL
FLUSH PRIVILEGES ;
DELETE FROM mysql.user ;
CREATE USER 'root'@'%' IDENTIFIED BY '${MYSQL_ROOT_PASSWORD}' ;
GRANT ALL ON *.* TO 'root'@'%' WITH GRANT OPTION ;
DROP DATABASE IF EXISTS test ;
CREATE DATABASE IF NOT EXISTS lynxc DEFAULT CHARACTER SET utf8 ;
CREATE USER 'lynx'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}' ;
GRANT ALL ON lynxc.* TO 'lynx'@'%' ;
FLUSH PRIVILEGES ;
EOSQL
    echo 'Appending tables to mysql-init file'
    cat '/config/tables.sql' >> "$MySqlInitFile"

fi

echo 'Executing mysqld_safe'
exec mysqld_safe
