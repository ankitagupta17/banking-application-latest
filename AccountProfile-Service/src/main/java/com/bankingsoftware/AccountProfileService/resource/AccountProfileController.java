package com.bankingsoftware.AccountProfileService.resource;

import com.bankingsoftware.AccountProfileService.Model.AccountProfileModel;
import com.bankingsoftware.AccountProfileService.ValueObject.AccountDetailsModel;
import com.bankingsoftware.AccountProfileService.ValueObject.ResponseTemplateVO;
import com.bankingsoftware.AccountProfileService.service.AccountProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banking")
public class AccountProfileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountProfileService accountProfileService;



    @GetMapping("/getProfile/{account_no}")
    public Optional<AccountProfileModel> getUserProfile(@PathVariable String account_no)
    {
        logger.info("getUserProfile Controller --> Fetching User Profile Data");
        return accountProfileService.getAccountProfile(account_no);
    }

    @GetMapping("/{account_no}")
    public ResponseTemplateVO getAccountInfoByAccNo(@PathVariable String account_no)
    {
        return accountProfileService.getProfileWithAccDetails(account_no);
    }

    @PostMapping("/createAccountWithProfile")
    public void createAccount(@RequestBody AccountDetailsModel accountDetailsModel)
    {
        logger.info("Create Account WithProfile Controller--->");
        accountProfileService.addAccount(accountDetailsModel);
        logger.info("newAccount_no--->"+accountDetailsModel.getAccount_no());
        accountProfileService.addProfile(accountDetailsModel.getAccount_no());
    }

    @GetMapping("/getAllProfiles")
    public List<AccountProfileModel> getAllProfile()
    {
        return  accountProfileService.getAllProfile();
    }


}
