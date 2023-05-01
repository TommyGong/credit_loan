package com.tommy.creditloan.web.controller;

import com.tommy.creditloan.service.UserAuthService;
import com.tommy.creditloan.model.LoanUser;
import com.tommy.creditloan.web.resp.CreditLoanResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/login", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api(tags = { "Login" })
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class);

    private final UserAuthService loginService;

    public LoginController(UserAuthService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value = "login")
    @PostMapping(value = "/login")
    public CreditLoanResp<String> login(@ApiParam(value = "userName") @Valid @RequestParam("userName")  String userName,
                                        @ApiParam(value = "password") @Valid @RequestParam("password")  String password) {
        log.info("[*** User login, param:{} ***]", userName, password);
        LoanUser user = loginService.login(userName, password);
        if (user == null) {
            return CreditLoanResp.error("System internal error.");
        }
        // TODO: add token to session
        return CreditLoanResp.ok(user.getUserId());
    }

    @ApiOperation(value = "logout")
    @PostMapping(value = "/logout")
    public CreditLoanResp<Boolean> logout(@ApiParam(value = "userId") @Valid @RequestParam("userId")  String userId) {
        log.info("[*** User logout, param:{} ***]", userId);
        // TODO： remove user token from session
        return CreditLoanResp.ok(true);
    }
}
