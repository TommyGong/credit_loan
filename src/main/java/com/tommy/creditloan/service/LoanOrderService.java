package com.tommy.creditloan.service;

import com.tommy.creditloan.model.LoanOrder;

import java.util.List;

public interface LoanOrderService {

    public LoanOrder applyLoanOrder(LoanOrder applyOrder);

    public List<LoanOrder> queryLoanOrder(LoanOrder applyOrder);

}
