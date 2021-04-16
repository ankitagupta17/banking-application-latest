package com.bankingsoftware.AccountDetailsService.service;

import com.bankingsoftware.AccountDetailsService.Model.AccountDetailsModel;
import com.bankingsoftware.AccountDetailsService.repository.AccountDetailsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountDetailsDAO accountDetailsDAO;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountDetailsService(AccountDetailsDAO accountDetailsDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountDetailsDAO = accountDetailsDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<AccountDetailsModel> getAccountDetails(String accountNumber) {

        return accountDetailsDAO.findById(accountNumber);
    }

    public void addAccount(AccountDetailsModel accountDetailsModel) {
        logger.info("Create Account Service--->");
        accountDetailsModel.setPassword(bCryptPasswordEncoder.encode(accountDetailsModel.getPassword()));
        accountDetailsDAO.save(accountDetailsModel);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountDetailsModel accountDetailsModel=accountDetailsDAO.findByEmail(s);
        if(accountDetailsModel==null) throw new UsernameNotFoundException(s);
        return new User(accountDetailsModel.getEmail(), accountDetailsModel.getPassword(), true,true,true,true, new ArrayList<>());
    }

    public AccountDetailsModel getUserDetailsByEmail(String email)
    {
        AccountDetailsModel accountDetailsModel=accountDetailsDAO.findByEmail(email);
        if(accountDetailsModel==null) throw new UsernameNotFoundException(email);
        return accountDetailsModel;
    }
}
