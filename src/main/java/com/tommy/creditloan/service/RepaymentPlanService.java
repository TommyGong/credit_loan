package com.tommy.creditloan.service;

import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RepaymentPlan;

import java.util.List;

public interface RepaymentPlanService {

    public List<RepaymentPlan> calculateRepaymentPlan(LoanOrder order);

    public List<RepaymentPlan> queryRepaymentHistory(String loanOrderId);

    public List<RepaymentPlan> queryRepaymentList(RepaymentPlan query);

    public int calculatePunishInterest(RepaymentPlan repaymentPlan);

}
