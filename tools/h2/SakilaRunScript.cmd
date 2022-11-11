java -cp .\sakila-server-procs-1.0.0.jar;.\h2-2.1.214.jar org.h2.tools.RunScript -url jdbc:h2:tcp://localhost:9092/sakila -user sa -script data/sakila-sql.zip -options compression zip
