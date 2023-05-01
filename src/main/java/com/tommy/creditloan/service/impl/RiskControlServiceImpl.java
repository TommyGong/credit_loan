package com.tommy.creditloan.service.impl;

import com.tommy.creditloan.service.RiskControlService;
import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RiskControlResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RiskControlServiceImpl implements RiskControlService {
    @Override
    public RiskControlResult checkLoanApplication(LoanOrder order) {
        // It is necessary to assess the user's credit risk and calculate their credit limit.
        RiskControlResult result = new RiskControlResult();
        result.setSuccess(true);
        result.setCreditLimit(new BigDecimal("50000.00"));
        return result;
    }

}
