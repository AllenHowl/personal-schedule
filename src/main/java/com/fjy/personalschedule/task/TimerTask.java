package com.fjy.personalschedule.task;


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

    @PostConstruct
    public void init(){
        checkAoto();
        reportCurrent();
        try {
            int i = 1/0;
        } catch (Exception e) {
            log.error("something error, e = ",e);
        }

    }

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

}
