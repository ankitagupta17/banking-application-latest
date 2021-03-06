package com.bankingsoftware.AccountProfileService.resource;

import com.bankingsoftware.AccountProfileService.Exception.AccountDetailsNotFoundException;
import com.bankingsoftware.AccountProfileService.Exception.AccountProfileNotFoundException;
import com.bankingsoftware.AccountProfileService.Exception.WrongDetailsException;
import com.bankingsoftware.AccountProfileService.Model.AccountProfileModel;
import com.bankingsoftware.AccountProfileService.ValueObject.AccountDetailsModel;
import com.bankingsoftware.AccountProfileService.ValueObject.ResponseTemplateVO;
import com.bankingsoftware.AccountProfileService.service.AccountProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        Optional<AccountProfileModel> accountProfile= accountProfileService.getAccountProfile(account_no);
        if(accountProfile.isEmpty()) {
            throw new AccountProfileNotFoundException("Account Profile not found with account number " + account_no);
        }
        return accountProfile;
    }

    @GetMapping("/{account_no}")
    public ResponseTemplateVO getAccountInfoByAccNo(@PathVariable String account_no)
    {
        ResponseTemplateVO accountInfo=accountProfileService.getProfileWithAccDetails(account_no);
        if(accountInfo.getAccountDetailsModel()==null)
        {
            throw new AccountDetailsNotFoundException(account_no);
        }
        return accountInfo;
    }

    @PostMapping("/createAccountWithProfile")
    public void createAccount(@Valid @RequestBody AccountDetailsModel accountDetailsModel)
    {
        try {
            logger.info("Create Account WithProfile Controller--->");
            accountProfileService.addAccount(accountDetailsModel);
            logger.info("newAccount_no--->" + accountDetailsModel.getAccount_no());
            accountProfileService.addProfile(accountDetailsModel.getAccount_no());
        }
        catch(Exception e)
        {
            logger.info("exception thrown ---->");
            throw new WrongDetailsException(e.getMessage());
        }
    }

    @GetMapping("/getAllProfiles")
    public List<AccountProfileModel> getAllProfile()
    {
        return  accountProfileService.getAllProfile();
    }


}
