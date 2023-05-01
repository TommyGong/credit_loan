package com.tommy.creditloan.service;

import com.tommy.creditloan.model.LoanProduct;

import java.util.List;

public interface LoanProductService {

    public int add(LoanProduct product);

    public int update(LoanProduct product);

    public List<LoanProduct> queryProduct(LoanProduct product);

}
