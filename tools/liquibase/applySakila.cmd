@REM Liquibase
@set SCHEMA=--liquibase-schema-name=sakila
@set DRIVER=--driver=org.h2.Driver
@set SEARCH_PATH=--search-path=W:/alt-workspace/H2-sakila
@REM set CHANGE_LOG=--changeLogFile=tools/changesets/h2-sakila-schema-sql.xml
@set CHANGE_LOG=--changeLogFile=tools/changesets/h2-sakila-schema.sql
@set DB_URL=--url="jdbc:h2:tcp://localhost:9092/sakila"
@set DB_USER=--username=sa
@set DB_PASSWORD=
@call liquibase %DRIVER% %SCHEMA% %SEARCH_PATH% %CHANGE_LOG% %DB_URL% %DB_USER% %DB_PASSWORD% %1 %2 %3
