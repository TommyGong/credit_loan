package com.tommy.creditloan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RiskControlResult implements Serializable {

    private static final long serialVersionUID = -5790326252370718819L;

    private boolean isSuccess;

    private String errorCode;

    private BigDecimal creditLimit;

    private Date createDate;
    private Date updateDate;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
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
