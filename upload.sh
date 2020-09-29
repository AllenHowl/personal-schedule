#!/bin/sh

rm -rf target/personal-schedule-0.0.1-SNAPSHOT.jar

ssh ali "rm -rf /data/springboot/personal-schedule-0.0.2-SNAPSHOT.jar"

mvn clean package -Dmaven.skip.test=true

cd target/

scp ./personal-schedule-0.0.2-SNAPSHOT.jar root@123.56.245.6:/data/springboot

ssh ali "nohup java -jar /data/springboot/personal-schedule-0.0.2-SNAPSHOT.jar > /data/log/log 2>&1 &"

