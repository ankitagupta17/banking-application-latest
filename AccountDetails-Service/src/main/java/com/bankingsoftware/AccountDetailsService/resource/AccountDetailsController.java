package com.bankingsoftware.AccountDetailsService.resource;

import com.bankingsoftware.AccountDetailsService.Model.AccountDetailsModel;
import com.bankingsoftware.AccountDetailsService.service.AccountDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class AccountDetailsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountDetailsService accountDetailsService;


    @GetMapping("/{accountNumber}")
    public Optional<AccountDetailsModel> getAccountDetails(@PathVariable String accountNumber)
    {
        return accountDetailsService.getAccountDetails(accountNumber);
    }


    @PostMapping("/createAccount")
    public void createAccount(@RequestBody AccountDetailsModel accountDetailsModel)
    {
        logger.info("Create Account Controller--->");
        accountDetailsService.addAccount(accountDetailsModel);
    }

}
