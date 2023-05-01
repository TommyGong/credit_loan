package com.tommy.creditloan.service.impl;

import com.tommy.creditloan.dao.LoanOrderMapper;
import com.tommy.creditloan.dao.RepaymentPlanMapper;
import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RepaymentPlan;
import com.tommy.creditloan.model.RiskControlResult;
import com.tommy.creditloan.service.LoanOrderService;
import com.tommy.creditloan.service.RepaymentPlanService;
import com.tommy.creditloan.service.RiskControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LoanOrderServiceImpl implements LoanOrderService {

    @Autowired
    private RiskControlService riskControlService;

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Autowired
    private LoanOrderMapper loanOrderMapper;

    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;

    @Override
    public LoanOrder applyLoanOrder(LoanOrder applyOrder) {
        // 1. validate by Risk Control Service
        RiskControlResult riskControlResult = riskControlService.checkLoanApplication(applyOrder);
        if ( ! riskControlResult.isSuccess()) {
            return null;
        }

        // 2. build loan order
        fullFilledLoanOrder(applyOrder, riskControlResult);

        // 3. save loan order
        loanOrderMapper.insert(applyOrder);

        // 4. generator repayment plan and save
        List<RepaymentPlan> repaymentDetails = repaymentPlanService.calculateRepaymentPlan(applyOrder);
        if ( ! repaymentDetails.isEmpty()) {
            for (RepaymentPlan plan : repaymentDetails) {
                repaymentPlanMapper.insert(plan);
            }
        }

        return applyOrder;
    }

    private static void fullFilledLoanOrder(LoanOrder applyOrder, RiskControlResult riskControlResult) {
        BigDecimal loanAmount = applyOrder.getLoanAmount().min(riskControlResult.getCreditLimit());
        applyOrder.setLoanAmount(loanAmount);
        // TODO: orderId must generate by some business rules, it's a temp method.
        applyOrder.setOrderId(UUID.randomUUID().toString());
        applyOrder.setCreateDate(new Date());
        applyOrder.setUpdateDate(new Date());
    }

    @Override
    public List<LoanOrder> queryLoanOrder(LoanOrder applyOrder) {
//        Example example = new Example(LoanOrder.class);
//        example.orderBy("sortId").asc();
//        example.createCriteria().andEqualTo("userId", applyOrder.getUserId());
        List<LoanOrder> rcRuleConfigList = loanOrderMapper.select(applyOrder);
        return rcRuleConfigList;
    }
}
