package com.tommy.creditloan.service;

import com.tommy.creditloan.model.LoanUser;

public interface UserAuthService {

    public LoanUser login(String userName, String password);

    public boolean logout(String userId);

}
