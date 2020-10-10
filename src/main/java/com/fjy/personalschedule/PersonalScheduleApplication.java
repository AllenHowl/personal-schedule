package com.fjy.personalschedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.fjy.personalschedule"})
@EnableScheduling
@Slf4j
public class PersonalScheduleApplication {

    public static void main(String[] args) {
        log.info("The persional-schedule is starting...");
        SpringApplication.run(PersonalScheduleApplication.class, args);
        log.info("The process finish start!");
    }

}
