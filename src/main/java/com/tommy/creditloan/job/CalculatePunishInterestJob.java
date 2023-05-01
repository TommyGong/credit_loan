package com.tommy.creditloan.job;

import com.tommy.creditloan.enums.RepaymentStatusEnum;
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
 * Calculate punish interest for overdue users.
 */
public class CalculatePunishInterestJob implements Job {

    Logger log = LoggerFactory.getLogger(CalculatePunishInterestJob.class);

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
        log.info("[*** CalculatePunishInterestJob running every day. ***]");
        // query all records need to repayment
        RepaymentPlan query = new RepaymentPlan();
        query.setRepayDate(DateUtils.getFormatDate(new Date()));
        query.setRepayStatus(RepaymentStatusEnum.OVERDUE.name());
        List<RepaymentPlan> repaymentPlans = repaymentPlanService.queryRepaymentList(query);
        if (repaymentPlans.isEmpty()) {
            log.info("[*** CalculatePunishInterestJob query 0 overdue record from DB. ***]");
            return;
        }

        for (RepaymentPlan repaymentPlan : repaymentPlans) {
            log.info("[*** CalculatePunishInterestJob calculate punish interest for overdue. params={}***]", repaymentPlan);
            repaymentPlanService.calculatePunishInterest(repaymentPlan);
        }

    }

}
