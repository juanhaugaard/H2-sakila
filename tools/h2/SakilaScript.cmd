@SET DB_URL=jdbc:h2:tcp://localhost:9092/sakila
@SET USER=-user sa
@SET PASSWORD=
@REM SET PASSWORD=-password <pwd>
@SET FILENAME=data/sakila-sql.zip
@SET CLASS_PATH=-cp .\sakila-server-procs-1.0.0.jar;.\h2-2.2.224.jar
@SET OPTIONS=-options NODATA NOPASSWORDS compression zip
@SET TABLES=TABLE CATEGORY,COUNTRY,CITY,ADDRESS,LANGUAGE,ACTOR,STORE,STAFF,STORE,CUSTOMER,FILM,FILM_TEXT,INVENTORY,RENTAL,PAYMENT,FILM_ACTOR,FILM_CATEGORY
@SET CMD=java %CLASS_PATH% org.h2.tools.Script -url %DB_URL% %USER% %PASSWORD% -script %FILENAME% %OPTIONS% %TABLES%
@ECHO %CMD%
@%CMD%
