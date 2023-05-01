package com.tommy.creditloan.web.controller;

import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.model.RepaymentPlan;
import com.tommy.creditloan.service.RepayByOuterService;
import com.tommy.creditloan.web.resp.CreditLoanResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/repay", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(tags = { "Repayment" })
public class RepaymentController {

    Logger log = LoggerFactory.getLogger(RepaymentController.class);

    @Autowired
    private RepayByOuterService repayByOuterService;

    @ApiOperation(value = "repay")
    @PostMapping(value = "/repay")
    public CreditLoanResp<Boolean> repay(@ApiParam(value = "loan order") @RequestBody RepaymentPlan repaymentPlan) {
        log.info("[*** User repayment, param:{} ***]", repaymentPlan);
        boolean repay = repayByOuterService.repay(repaymentPlan);
        if ( ! repay) {
            return CreditLoanResp.error("System internal error.");
        }
        return CreditLoanResp.ok(repay);
    }

    @ApiOperation(value = "repayAll")
    @PostMapping(value = "/repayAll")
    public CreditLoanResp<Boolean> repayAll(@ApiParam(value = "loan order") @RequestBody LoanOrder loanOrder) {
        log.info("[*** User repayAll, param:{} ***]", loanOrder);
        boolean logoutFlag = repayByOuterService.repayAll(loanOrder);

        return CreditLoanResp.ok(logoutFlag);
    }
}
