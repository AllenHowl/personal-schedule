#!/bin/sh

mvn clean package -Dmaven.skip.test=true

cd target/

scp ./personal-schedule-0.0.4-Release.jar root@123.56.245.6:/data/springboot



