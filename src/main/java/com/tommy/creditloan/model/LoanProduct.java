package com.tommy.creditloan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "loan_product")
public class LoanProduct implements Serializable {

    private static final long serialVersionUID = 5508157585256865916L;

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "annual_interest_rate")
    private BigDecimal annualInterestRate;

    @Column(name = "loan_min")
    private BigDecimal loanMin;

    @Column(name = "loan_max")
    private BigDecimal loanMax;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public BigDecimal getLoanMin() {
        return loanMin;
    }

    public void setLoanMin(BigDecimal loanMin) {
        this.loanMin = loanMin;
    }

    public BigDecimal getLoanMax() {
        return loanMax;
    }

    public void setLoanMax(BigDecimal loanMax) {
        this.loanMax = loanMax;
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
