#!/bin/sh
# Liquibase
#SCHEMA=--liquibase-schema-name=SAKILA
SCHEMA=--liquibase-schema-name=PUBLIC
DRIVER=--driver=org.h2.Driver
SEARCH_PATH=--search-path=../..
#CHANGE_LOG=--changeLogFile=tools/changesets/h2-sakila-schema-sql.xml
CHANGE_LOG=--changeLogFile=tools/changesets/h2-sakila-schema.sql
DB_URL=--url="jdbc:h2:tcp://localhost:9092/sakila"
DB_USER=--username=sa
DB_PASSWORD=
./liquibase.sh $DRIVER $SCHEMA $SEARCH_PATH $CHANGE_LOG $DB_URL $DB_USER $DB_PASSWORD "$@"
