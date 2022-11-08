@REM Liquibase
@set changeLogFile=sakila.xml
@del %changeLogFile%
@set SCHEMA=sakila
@set DRIVER=--driver=com.mysql.cj.jdbc.Driver
@REM set OPTS=--diffTypes="tables, views, columns, indexes, foreignkeys, primarykeys, uniqueconstraints, data"
@set OPTS=
@set CHANGE_LOG=--changeLogFile=%changeLogFile%
@set DB_URL=--url="jdbc:mysql://localhost:3306/%SCHEMA%?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=TRUE"
@REM set DB_URL=--url="jdbc:mysql://localhost:3306/%SCHEMA%?serverTimezone=UTC&relaxAutoCommit=TRUE&useSSL=FALSE"
@REM set DB_USER=--username=backstage
@REM set DB_PASSWORD=--password=b@ckst@ge123
@set DB_USER=--username=root
@set DB_PASSWORD=--password=root
call liquibase %DRIVER% %OPTS% %CHANGE_LOG% %DB_URL% %DB_USER% %DB_PASSWORD% generateChangeLog
