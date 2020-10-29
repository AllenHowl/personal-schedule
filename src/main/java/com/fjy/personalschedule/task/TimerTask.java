package com.fjy.personalschedule.task;


import com.fjy.personalschedule.task.tasks.AFunTask;
import com.fjy.personalschedule.task.tasks.AoTuGetCarMessageTask;
import com.fjy.personalschedule.task.tasks.XinRenXinShiClockInTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j(topic = "performance")
public class TimerTask {

    @Autowired
    private XinRenXinShiClockInTask xinRenXinShiClockInTask;

    @Autowired
    private AoTuGetCarMessageTask aoTuGetCarMessageTask;

    @Autowired
    private AFunTask aFunTask;

    @PostConstruct
    public void init(){
        /*checkAoto();
        reportCurrent();*/
        aFunCheckIn();

    }

    //**************************************凹凸租车start**************************************
    /**
     * 检查是否有符合预期结果
     */
    @Scheduled(cron = "0 */2 * * 9 ?")
    public void checkAoto(){
        log.info("schedule task start ....");
        aoTuGetCarMessageTask.taskProcess();
    }

    /**
     * 汇报当前最低信息
     */
    @Scheduled(cron = "0 */30 * * 9 ?")
    public void reportCurrent(){
        aoTuGetCarMessageTask.sendPoorMessage();
    }

    //**************************************阿凡vpn签到start**************************************

    /**
     * 阿凡vpn签到
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void aFunCheckIn(){
        aFunTask.taskProcess();
    }
}
