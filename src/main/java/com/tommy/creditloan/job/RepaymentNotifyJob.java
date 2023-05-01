package com.tommy.creditloan.job;

import com.tommy.creditloan.model.RepaymentPlan;
import com.tommy.creditloan.service.RepaymentPlanService;
import com.tommy.creditloan.utils.DateUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Daily notify users for repayment
 */
public class RepaymentNotifyJob implements Job {

    Logger log = LoggerFactory.getLogger(RepaymentNotifyJob.class);

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            executeTask(jobExecutionContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeTask(JobExecutionContext jobExecutionContext) throws SchedulerException {

        JobKey key = jobExecutionContext.getJobDetail().getKey();
        log.info("[*** RepaymentNotifyJob running every hour. ***]");
        // query all records need to repayment
        RepaymentPlan query = new RepaymentPlan();
        query.setRepayDate(DateUtils.getFormatDate(new Date()));
        List<RepaymentPlan> repaymentPlans = repaymentPlanService.queryRepaymentList(query);
        if (repaymentPlans.isEmpty()) {
            log.info("[*** RepaymentNotifyJob query 0 record from DB. ***]");
            return;
        }

        for (RepaymentPlan repaymentPlan : repaymentPlans) {
            // TODO: SMS notify user for repayment

        }

    }

}
