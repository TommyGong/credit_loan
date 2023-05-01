package com.tommy.creditloan.service;

import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RiskControlResult;

public interface RiskControlService {

    public RiskControlResult checkLoanApplication(LoanOrder order);

}
