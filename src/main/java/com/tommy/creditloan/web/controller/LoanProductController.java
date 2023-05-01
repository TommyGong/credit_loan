package com.tommy.creditloan.web.controller;

import com.tommy.creditloan.service.LoanProductService;
import com.tommy.creditloan.model.LoanProduct;
import com.tommy.creditloan.web.resp.CreditLoanResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/loanproduct", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(tags = { "LoanProduct" })
public class LoanProductController {

    Logger log = LoggerFactory.getLogger(LoanProductController.class);

    private final LoanProductService loanProductService;

    public LoanProductController(LoanProductService loanProductService) {
        this.loanProductService = loanProductService;
    }

    @ApiOperation(value = "product_add")
    @PostMapping(value = "/add")
    public CreditLoanResp<String> add(@ApiParam(value = "loan product") @RequestBody LoanProduct product) {
        log.info("[*** User add new loan product, param:{} ***]", product);
        int i = loanProductService.add(product);
        if (i < 0) {
            return CreditLoanResp.error("System internal error.");
        }
        return CreditLoanResp.ok();
    }

    @ApiOperation(value = "product_update")
    @PostMapping(value = "/update")
    public CreditLoanResp<String> update(@ApiParam(value = "loan product") @RequestBody LoanProduct product) {
        log.info("[*** User update loan product, param:{} ***]", product);
        int i = loanProductService.update(product);
        if (i < 0) {
            return CreditLoanResp.error("System internal error.");
        }
        return CreditLoanResp.ok();
    }

    @ApiOperation(value = "product_query")
    @PostMapping(value = "/query")
    public CreditLoanResp<List> query(@ApiParam(value = "loan product") @RequestBody LoanProduct product) {
        log.info("[*** User query loan product, param:{} ***]", product);
        List<LoanProduct> list = loanProductService.queryProduct(product);

        return CreditLoanResp.ok(list);
    }
}
