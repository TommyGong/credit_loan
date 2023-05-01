package com.tommy.creditloan.service.impl;

import com.tommy.creditloan.dao.RepaymentPlanMapper;
import com.tommy.creditloan.enums.RepaymentStatusEnum;
import com.tommy.creditloan.service.RepaymentPlanService;
import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RepaymentPlanServiceImpl implements RepaymentPlanService {

    private final static Integer REPAY_DAY = 10; // repayment day of every month
    private final static String REPAY_DATE_FORMAT = "dd/MM/yyyy";
    public static final String MONTHS = "12";

    public static final BigDecimal PUNISH_INTEREST_RATE = new BigDecimal("0.01");

    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;

    @Override
    public List<RepaymentPlan> calculateRepaymentPlan(LoanOrder order) {
        List<RepaymentPlan> repaymentDetails = new ArrayList<>();

        BigDecimal monthlyInterestRate = order.getAnnualInterestRate().divide(new BigDecimal(100)).divide(new BigDecimal(MONTHS), 8, BigDecimal.ROUND_HALF_UP);
        BigDecimal divisor = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(BigDecimal.ONE.add(monthlyInterestRate).pow(order.getItems()), 8, BigDecimal.ROUND_HALF_UP));
        BigDecimal monthlyRepayment = order.getLoanAmount().multiply(monthlyInterestRate).divide(divisor, 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal remainingLoanAmount = order.getLoanAmount();
        for (int i = 1; i <= order.getItems(); i++) {
            BigDecimal interest = remainingLoanAmount.multiply(monthlyInterestRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal principal = monthlyRepayment.subtract(interest);
            remainingLoanAmount = remainingLoanAmount.subtract(principal);

            RepaymentPlan repaymentDetail = new RepaymentPlan(order.getOrderId(), order.getUserId(),
                    i, monthlyRepayment, principal, interest, remainingLoanAmount,
                    RepaymentStatusEnum.INIT.name(), generateRepaymentDate(i));
            repaymentDetail.setCreateDate(new Date());
            repaymentDetail.setUpdateDate(new Date());
            repaymentDetails.add(repaymentDetail);
        }

        return repaymentDetails;
    }

    @Override
    public List<RepaymentPlan> queryRepaymentHistory(String loanOrderId) {
        RepaymentPlan query = new RepaymentPlan();
        query.setLoanOrderId(loanOrderId);
        return repaymentPlanMapper.select(query);
    }

    @Override
    public List<RepaymentPlan> queryRepaymentList(RepaymentPlan query) {
        return repaymentPlanMapper.select(query);
    }

    @Override
    public int calculatePunishInterest(RepaymentPlan repaymentPlan) {
        int overdueDays = calculateOverdueDays(repaymentPlan.getRepayDate());
        BigDecimal punishInterest = PUNISH_INTEREST_RATE.multiply(new BigDecimal(overdueDays));
        repaymentPlan.setPunishInterest(punishInterest);
        repaymentPlan.setUpdateDate(new Date());
        return repaymentPlanMapper.updateByPrimaryKeySelective(repaymentPlan);
    }

    /**
     * generate repayment date with current date and index
     *
     * @param index
     * @return
     */
    private String generateRepaymentDate(int index) {
        SimpleDateFormat sdf = new SimpleDateFormat(REPAY_DATE_FORMAT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, index);
        c.set(Calendar.DAY_OF_MONTH, REPAY_DAY);
        return sdf.format(c.getTime());
    }

    private int calculateOverdueDays(String repayDate) {
        SimpleDateFormat df = new SimpleDateFormat(REPAY_DATE_FORMAT);
        try {
            Date date1 = df.parse(repayDate);
            Date date2 = new Date();
            long diff = date2.getTime() - date1.getTime();
            return (int) diff / (1000 * 60 * 60 * 24);
        } catch (Exception ex) {

        }
        return 0;
    }
}
