package com.tommy.creditloan.model;

import com.tommy.creditloan.enums.RepaymentStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "repayment_plan")
public class RepaymentPlan implements Serializable {

    private static final long serialVersionUID = 9001077281272373038L;

    public RepaymentPlan(String loanOrderId, String userId,
                         int item, BigDecimal monthlyRepayment,
                         BigDecimal principal, BigDecimal interest,
                         BigDecimal remainingLoanAmount,
                         String repayStaus,
                         String repayDate) {
        this.loanOrderId = loanOrderId;
        this.userId = userId;
        this.item = item;
        this.monthlyRepayment = monthlyRepayment;
        this.principal = principal;
        this.interest = interest;
        this.remainingLoanAmount = remainingLoanAmount;
        this.repayStatus = repayStaus;
        this.repayDate = repayDate;
    }

    public RepaymentPlan() {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_order_id")
    private String loanOrderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "item")
    private Integer item;

    @Column(name = "monthly_repayment")
    private BigDecimal monthlyRepayment;

    @Column(name = "principal")
    private BigDecimal principal;

    @Column(name = "interest")
    private BigDecimal interest;

    @Column(name = "remaining_loan_amount")
    private BigDecimal remainingLoanAmount;

    @Column(name = "punish_interest")
    private BigDecimal punishInterest;

    @Column(name = "repay_date")
    private String repayDate;

    @Column(name = "actual_repayment")
    private BigDecimal actualRepayment;

    @Column(name = "repay_status")
    private String repayStatus;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanOrderId() {
        return loanOrderId;
    }

    public void setLoanOrderId(String loanOrderId) {
        this.loanOrderId = loanOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRemainingLoanAmount() {
        return remainingLoanAmount;
    }

    public void setRemainingLoanAmount(BigDecimal remainingLoanAmount) {
        this.remainingLoanAmount = remainingLoanAmount;
    }

    public BigDecimal getPunishInterest() {
        return punishInterest;
    }

    public void setPunishInterest(BigDecimal punishInterest) {
        this.punishInterest = punishInterest;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public BigDecimal getActualRepayment() {
        return actualRepayment;
    }

    public void setActualRepayment(BigDecimal actualRepayment) {
        this.actualRepayment = actualRepayment;
    }

    public String getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(String repayStatus) {
        this.repayStatus = repayStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
