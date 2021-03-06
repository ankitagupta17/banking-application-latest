package com.bankingsoftware.AccountDetailsService.service;

import com.bankingsoftware.AccountDetailsService.Model.AccountDetailsModel;
import com.bankingsoftware.AccountDetailsService.repository.AccountDetailsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountDetailsDAO accountDetailsDAO;


    public Optional<AccountDetailsModel> getAccountDetails(String accountNumber) {

        return accountDetailsDAO.findById(accountNumber);
    }

    public void addAccount(AccountDetailsModel accountDetailsModel) {

        logger.info("Create Account Service--->");
        accountDetailsDAO.save(accountDetailsModel);
        logger.info(" Account Created--->"+accountDetailsModel.getAccount_no());
       // return accountDetailsModel.getAccount_no();
    }
}
