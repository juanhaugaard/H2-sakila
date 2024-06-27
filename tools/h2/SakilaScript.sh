#!/bin/sh
DB_URL="jdbc:h2:tcp://localhost:9092/sakila"
USER="-user sa"
PASSWORD=
#PASSWORD="-password <pwd>"
FILENAME="./data/sakila-sql.zip"
CLASS_PATH="-cp ./sakila-server-procs-1.0.0.jar:./h2-2.2.224.jar"
OPTIONS="-options NODATA NOPASSWORDS compression zip"
TABLES="TABLE CATEGORY,COUNTRY,CITY,ADDRESS,LANGUAGE,ACTOR,STORE,STAFF,STORE,CUSTOMER,FILM,FILM_TEXT,INVENTORY,RENTAL,PAYMENT,FILM_ACTOR,FILM_CATEGORY"
CMD="java $CLASS_PATH org.h2.tools.Script -url $DB_URL $USER $PASSWORD -script $FILENAME $OPTIONS $TABLES"
echo $CMD
$CMD
