#!/usr/bin/env bash
MYSQL_INIT_FILE='/config/mysql-init.sql'
echo 'Checking the database server has been initialised'
if [ ! -e "$MYSQL_INIT_FILE" ]; then
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
    echo 'Installing System Tables'
    mysql_install_db --datadir="/var/lib/mysql"
    echo 'Creating mysql-init file.'
    echo 'Adding creation of users and databases to mysql-init'
    cat > "$MYSQL_INIT_FILE" <<-EOSQL
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
    echo 'Adding creation of db schema to mysql-init'
    cat '/config/schema.sql' >> "$MYSQL_INIT_FILE"
    echo 'Adding loading of reference data to mysql-init'
    declare -a tables=("language" "country" "country_lang" "email_template" "fact_type" "resource_layout")
    for table in "${tables[@]}";
    do
       echo "LOAD DATA INFILE '/config/${table}.csv' INTO TABLE ${table} CHARACTER SET utf8 FIELDS TERMINATED BY ',' ENCLOSED BY '\"' IGNORE 1 LINES ;" >> "$MYSQL_INIT_FILE"
    done;
fi
echo 'Executing mysqld_safe'
exec mysqld_safe
