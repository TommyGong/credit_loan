package com.tommy.creditloan.service.impl;

import com.tommy.creditloan.dao.RepaymentPlanMapper;
import com.tommy.creditloan.dao.UserInfoMapper;
import com.tommy.creditloan.enums.RepaymentStatusEnum;
import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RepaymentPlan;
import com.tommy.creditloan.model.RepaymentRequest;
import com.tommy.creditloan.model.UserInfo;
import com.tommy.creditloan.service.RepayByOuterService;
import com.tommy.creditloan.service.RepaymentPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RepayByOuterServiceImpl implements RepayByOuterService {

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean repay(RepaymentPlan request) {
        RepaymentPlan repaymentPlan = repaymentPlanMapper.selectByPrimaryKey(request.getId());
        RepaymentRequest outRequest = buildRepaymentRequest(repaymentPlan);
        // TODO: invoke outer third payment channel for real repayment
        Boolean repaymentResult = true;
        if (repaymentResult) {
            repaymentPlan.setRepayStatus(RepaymentStatusEnum.FINISHED.name());
        } else {
            repaymentPlan.setRepayStatus(RepaymentStatusEnum.FAILED.name());
        }
        repaymentPlan.setUpdateDate(new Date());

        return repaymentPlanMapper.updateByPrimaryKey(repaymentPlan) > 0;
    }


    @Override
    public boolean repayAll(LoanOrder loanOrder) {
        RepaymentPlan query = new RepaymentPlan();
        query.setLoanOrderId(loanOrder.getOrderId());
        List<RepaymentPlan> repaymentPlans = repaymentPlanService.queryRepaymentList(query);
        BigDecimal repayment = sumRepayAmount(repaymentPlans);
        RepaymentRequest request = buildRepaymentAllRequest(repayment, loanOrder.getUserId());
        // TODO: invoke outer third payment channel for real repayment
        Boolean repaymentResult = true;
        for (RepaymentPlan repaymentPlan : repaymentPlans) {
            if (repaymentResult) {
                repaymentPlan.setRepayStatus(RepaymentStatusEnum.FINISHED.name());
            } else {
                repaymentPlan.setRepayStatus(RepaymentStatusEnum.FAILED.name());
            }
            repaymentPlan.setUpdateDate(new Date());
            repaymentPlanMapper.updateByPrimaryKeySelective(repaymentPlan);
        }

        return true;
    }

    private RepaymentRequest buildRepaymentRequest(RepaymentPlan repaymentPlan) {
        RepaymentRequest request = new RepaymentRequest();
        request.setRepayAmount(repaymentPlan.getPrincipal().
                add(repaymentPlan.getInterest() == null ? new BigDecimal("0") : repaymentPlan.getInterest()).
                add(repaymentPlan.getPunishInterest() == null ? new BigDecimal("0") : repaymentPlan.getPunishInterest()));
        request.setRepayDate(new Date());
        request.setUserId(repaymentPlan.getUserId());
        UserInfo query = new UserInfo();
        query.setUserId(repaymentPlan.getUserId());
        List<UserInfo> userInfos = userInfoMapper.select(query);
        if ( ! userInfos.isEmpty() && userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            request.setBankAccount(userInfo.getBankAccount());
        }
        request.setCreateDate(new Date());
        request.setUpdateDate(new Date());

        return request;
    }

    private RepaymentRequest buildRepaymentAllRequest(BigDecimal repayAmount, String userId) {
        RepaymentRequest request = new RepaymentRequest();
        request.setRepayAmount(repayAmount);
        request.setRepayDate(new Date());
        request.setUserId(userId);
        UserInfo query = new UserInfo();
        query.setUserId(userId);
        List<UserInfo> userInfos = userInfoMapper.select(query);
        if ( ! userInfos.isEmpty() && userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            request.setBankAccount(userInfo.getBankAccount());
        }
        request.setCreateDate(new Date());
        request.setUpdateDate(new Date());

        return request;
    }

    private BigDecimal sumRepayAmount(List<RepaymentPlan> repaymentPlans) {
        BigDecimal repayAmount = new BigDecimal(0);
        if (repaymentPlans.isEmpty()) {
            return repayAmount;
        }

        for (RepaymentPlan repaymentPlan : repaymentPlans) {
            if (StringUtils.equals(repaymentPlan.getRepayStatus(), RepaymentStatusEnum.FINISHED.name())) {
                continue;
            }

            if (StringUtils.equals(repaymentPlan.getRepayStatus(), RepaymentStatusEnum.OVERDUE.name())) {
                repayAmount = repayAmount.add(repaymentPlan.getPrincipal()).
                        add(repaymentPlan.getInterest()).
                        add(repaymentPlan.getPunishInterest());
            } else {
                repayAmount = repayAmount.add(repaymentPlan.getPrincipal());
            }
        }
        return repayAmount;
    }
}
