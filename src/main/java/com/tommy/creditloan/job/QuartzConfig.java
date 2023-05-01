package com.tommy.creditloan.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean("repaymentNotifyJob")
    public JobDetail repaymentNotifyJobDetail() {
        return JobBuilder.newJob(RepaymentNotifyJob.class)
                .withIdentity("RepaymentNotifyJob")
                .usingJobData("msg", "Repayment notify job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger repaymentNotifyJobTrigger() {
        // running every day at 10:00 am
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("* * 10 * * ?");
        CronTrigger quartzTaskService = TriggerBuilder.newTrigger()
                .forJob(repaymentNotifyJobDetail())
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
        return quartzTaskService;
    }

    @Bean("repaymentAutomaticJob")
    public JobDetail repaymentAutomaticJobDetail() {
        return JobBuilder.newJob(RepaymentAutomaticJob.class)
                .withIdentity("RepaymenAutomaticJob")
                .usingJobData("msg", "Repayment automatic job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger repaymentAutomaticJobTrigger() {
        // running every day at 16:00 pm
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("* * 16 * * ?");
        CronTrigger quartzTaskService = TriggerBuilder.newTrigger()
                .forJob(repaymentAutomaticJobDetail())
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
        return quartzTaskService;
    }

    @Bean("calculatePunishInterestJob")
    public JobDetail calculatePunishInterestJobDetail() {
        return JobBuilder.newJob(CalculatePunishInterestJob.class)
                .withIdentity("CalculatePunishInterestJob")
                .usingJobData("msg", "Calculate punish interest job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger calculatePunishInterestJobTrigger() {
        // running every day at 02:00 am
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("* * 2 * * ?");
        CronTrigger quartzTaskService = TriggerBuilder.newTrigger()
                .forJob(repaymentAutomaticJobDetail())
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
        return quartzTaskService;
    }

}
