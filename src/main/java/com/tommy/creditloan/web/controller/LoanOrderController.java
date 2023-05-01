package com.tommy.creditloan.web.controller;

import com.tommy.creditloan.model.LoanOrder;
import com.tommy.creditloan.service.LoanOrderService;
import com.tommy.creditloan.web.resp.CreditLoanResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(tags = { "LoanOrder" })
public class LoanOrderController {

    Logger log = LoggerFactory.getLogger(LoanOrderController.class);

    private final LoanOrderService loanOrderService;

    public LoanOrderController(LoanOrderService loanOrderService) {
        this.loanOrderService = loanOrderService;
    }

    @ApiOperation(value = "apply")
    @PostMapping(value = "/apply")
    public CreditLoanResp<LoanOrder> apply(@ApiParam(value = "loan order") @RequestBody LoanOrder order) {
        log.info("[*** User apply loan, param:{} ***]", order);
        LoanOrder loanOrder = loanOrderService.applyLoanOrder(order);
        if (loanOrder == null) {
            return CreditLoanResp.error("System internal error.");
        }
        return CreditLoanResp.ok(loanOrder);
    }

    @ApiOperation(value = "query")
    @PostMapping(value = "/query")
    public CreditLoanResp<List> query(@ApiParam(value = "loan order") @RequestBody LoanOrder order) {
        log.info("[*** User query loan application, param:{} ***]", order);
        List<LoanOrder> loanOrders = loanOrderService.queryLoanOrder(order);

        return CreditLoanResp.ok(loanOrders);
    }

}
