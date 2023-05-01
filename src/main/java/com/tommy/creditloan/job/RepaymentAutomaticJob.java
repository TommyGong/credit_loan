package com.tommy.creditloan.job;

import com.tommy.creditloan.model.RepaymentPlan;
import com.tommy.creditloan.service.RepayByOuterService;
import com.tommy.creditloan.service.RepaymentPlanService;
import com.tommy.creditloan.utils.DateUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Daily repayment automatic for users
 */
public class RepaymentAutomaticJob implements Job {

    Logger log = LoggerFactory.getLogger(RepaymentAutomaticJob.class);

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Autowired
    private RepayByOuterService repayByOuterService;

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
        log.info("[*** RepaymenAutomaticJob repayment everyday. ***]");
        // query all records need to repayment
        RepaymentPlan query = new RepaymentPlan();
        query.setRepayDate(DateUtils.getFormatDate(new Date()));
        List<RepaymentPlan> repaymentPlans = repaymentPlanService.queryRepaymentList(query);
        if (repaymentPlans.isEmpty()) {
            log.info("[*** RepaymenAutomaticJob query 0 record from DB. ***]");
            return;
        }

        for (RepaymentPlan repaymentPlan : repaymentPlans) {
            log.info("[*** RepaymenAutomaticJob automatic repayment. params={}***]", repaymentPlan);
            repayByOuterService.repay(repaymentPlan);
        }

    }

}
