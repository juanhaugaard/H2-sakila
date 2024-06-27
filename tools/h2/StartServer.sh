#!/bin/sh
java -cp ./sakila-server-procs-1.0.0.jar:./h2-2.2.224.jar org.h2.tools.Server -web -tcp -pg -ifNotExists -baseDir ~/data
