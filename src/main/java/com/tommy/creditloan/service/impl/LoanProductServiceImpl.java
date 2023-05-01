package com.tommy.creditloan.service.impl;

import com.tommy.creditloan.dao.LoanProductMapper;
import com.tommy.creditloan.service.LoanProductService;
import com.tommy.creditloan.model.LoanProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LoanProductServiceImpl implements LoanProductService {

    @Autowired
    private LoanProductMapper loanProductMapper;

    @Override
    public int add(LoanProduct product) {
        fullFilledProduct(product);
        return loanProductMapper.insert(product);
    }

    @Override
    public int update(LoanProduct product) {
        product.setUpdateDate(new Date());
        return loanProductMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<LoanProduct> queryProduct(LoanProduct product) {
        //
        return loanProductMapper.select(product);
    }

    private static void fullFilledProduct(LoanProduct product) {
        // TODO: productId must generate by some business rules, it's a temp method.
        product.setProductId(UUID.randomUUID().toString());
        product.setCreateDate(new Date());
        product.setUpdateDate(new Date());
    }
}
