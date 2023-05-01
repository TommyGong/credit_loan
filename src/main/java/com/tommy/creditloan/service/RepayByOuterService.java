package com.tommy.creditloan.service;

import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RepaymentPlan;

public interface RepayByOuterService {

    public boolean repay(RepaymentPlan repaymentPlan);

    public boolean repayAll(LoanOrder loanOrder);

}
