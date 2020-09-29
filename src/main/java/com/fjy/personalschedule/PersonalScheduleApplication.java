package com.fjy.personalschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.fjy.personalschedule"})
@EnableScheduling
public class PersonalScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalScheduleApplication.class, args);
    }

}
