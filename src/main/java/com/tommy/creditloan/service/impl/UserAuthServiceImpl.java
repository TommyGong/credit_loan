package com.tommy.creditloan.service.impl;

import com.tommy.creditloan.dao.LoanUserMapper;
import com.tommy.creditloan.service.UserAuthService;
import com.tommy.creditloan.model.LoanUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin";

    @Autowired
    private LoanUserMapper loanUserMapper;

    @Override
    public LoanUser login(String userName, String password) {
        // TODO: Use Spring Security or Shiro to handle authentication and authorization in the real project.
        // TODO: only for POC
        LoanUser query = new LoanUser();
        query.setUserName(userName);
        List<LoanUser> existUsers = loanUserMapper.select(query);

        if ( ! existUsers.isEmpty() && existUsers.size() > 0) {
            LoanUser user = existUsers.get(0);
            // TODO: password should be encrypted with MD5 algorithm
            if (StringUtils.equals(password, user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean logout(String userId) {
        return false;
    }
}
