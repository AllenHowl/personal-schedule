#!/bin/bash

HOME_WORK="/home/QAuser"
API_MODEL_PATH="/home/QAuser/api-model"
WEIBOCLIENT_PATH="/home/QAuser/weiboclient4j"
COMMONS_PATH="/home/QAuser/commons"
YPZB_PATH="/home/QAuser/ypzb"
TOMCAT_PATH="/home/QAuser/local/tomcat_8080"
TOMCAT_NOVEL_PATH="/home/QAuser/local/tomcat_novel"
TOMCAT_MANBO_PATH="/home/QAuser/local/tomcat_manbo"
TOMCAT_OMS_PATH="/home/QAuser/local/tomcat_oms"
TOMCAT_TASK_PATH="/home/QAuser/local/tomcat_task"
SERVICE_PATH="/home/QAuser/local/service"

#ENV_DISCONF="env=test"
ENV_DISCONF="env=test_02"


if [ $# -eq 1 ]
then
    FEILNAME=$1
else
    echo "param error !"
    echo -e "please type: sh build_war.sh xxxx.tag.gz"
    exit
fi

function scp_func()
{
    echo  "start scp opt ……"
    cd $YPZB_PATH
    rm -rf *.war
    rm -rf *.gz
    #sshpass -puxinwork scp -P2223 test@61.149.7.229:/home/test/$FEILNAME .
    sshpass -p EBuhTaR6gXVXtLVK scp -P60088 test@192.168.111.16:/home/test/files/$FEILNAME .

    if [ $? -eq 0 ]; then
        continue
    else
        echo "scp failed !!!"
        exit
    fi
    FEIL=`echo $FEILNAME|awk -F "/" '{print $NF}'`
    tar zxvf $FEIL
}

function start_server()
{
    echo "start server..."
    cd $SERVICE_PATH/
    rm -rf uxinlive
    #mv uxinlive uxinlive_$(date +%Y%m%d%H%M%S)
    mkdir uxinlive
    cd uxinlive
    cp $YPZB_PATH/service-bootstrap.war .
    jar -xvf service-bootstrap.war
    rm -f service-bootstrap.war
    sed -i "s/^env=.*/$ENV_DISCONF/g" $SERVICE_PATH/uxinlive/WEB-INF/classes/disconf.properties
    cd ..
    sh running_service.sh stop
    sh running_service.sh start
"build_war.sh" 218L, 5396C                                                                                                                                                                                                                                    14,0-1        Top
    cd task
    cp $YPZB_PATH/web-task.war .
    jar -xvf web-task.war
    rm -f web-task.war
    sed -i "s/^env=.*/$ENV_DISCONF/g" $TOMCAT_TASK_PATH/webapps/task/WEB-INF/classes/disconf.properties
    #------start
    cd $TOMCAT_TASK_PATH/bin
    ./startup.sh
}

function reg_func()
{
    arr=("|" "/" "—" "\\")
    for((i=0;i<300;i++))
    do
        printf "\rwating process 20888[%s]" ${arr[(($i%4))]}
        num=`netstat -nlp|grep 20888 |wc -l`
        if [ $num -eq 1 ]; then
            printf "\n"
            printf "check process id 20888 success"
            printf "\n"
            printf "\n"
            break
        fi

        if [ $i -eq 300 ]; then
            printf "\r"
            printf "check process id 20888 failed !!!"
            printf "\r"
            exit
        fi
        sleep 0.2
    done

    sleep 4

    #reg motan
    rm -f tmp_in
    mknod tmp_in p
    exec 8<> tmp_in

    #process custom command
    telnet 127.0.0.1 20888 <&8 &
    echo "reg" >> tmp_in
    echo "cstart" >> tmp_in
    echo "q" >> tmp_in
}

scp_func
start_server
sleep 90
start_client
start_novel_portal
start_manbo_client
start_oms
start_task

ps -ef|grep uxin
ps -ef|grep tomcat

reg_func