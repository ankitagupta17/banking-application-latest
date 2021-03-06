package com.bankingsoftware.AccountProfileService.service;

import com.bankingsoftware.AccountProfileService.Model.AccountProfileModel;
import com.bankingsoftware.AccountProfileService.ValueObject.AccountDetailsModel;
import com.bankingsoftware.AccountProfileService.ValueObject.ResponseTemplateVO;
import com.bankingsoftware.AccountProfileService.repository.AccountProfileDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountProfileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountProfileDAO accountProfileDAO;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<AccountProfileModel> getAccountProfile(String account_no)
    {
        logger.info("getAccountProfile Service --> Fetching User Profile Data");
        if(accountProfileDAO.findById(account_no).isEmpty())
        {

            //return Arr"Account Not Found";
        }
        return accountProfileDAO.findById(account_no);
    }

    public ResponseTemplateVO getProfileWithAccDetails(String account_no) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Optional<AccountProfileModel> accountProfileModel = accountProfileDAO.findById(account_no);
        AccountDetailsModel accountDetailsModel = restTemplate.getForObject("http://ACCOUNT-REFERENCE-DATA-SERVICE/users/"+account_no,AccountDetailsModel.class);
        responseTemplateVO.setAccountDetailsModel(accountDetailsModel);
        responseTemplateVO.setAccountProfileModel(accountProfileModel);
        return responseTemplateVO;

    }

    public void addAccount(AccountDetailsModel accountDetailsModel) {


        String acc_no = accountDetailsModel.getAccount_no();
        AccountDetailsModel newacc = restTemplate.postForObject("http://ACCOUNT-REFERENCE-DATA-SERVICE/users/createAccount",accountDetailsModel,AccountDetailsModel.class);

        //logger.info("createdAccount getAccount_no-->"+createdAccount.getAccount_no());
        logger.info("createdAccount---------------->");

      //  return createdAccount.getAccount_no();

    }

    public void addProfile(String newAccount_no) {
        Random random = new Random();
        String pin = String.format("%04d", random.nextInt(10000));
        AccountProfileModel newProfile = new AccountProfileModel(newAccount_no,pin);
        accountProfileDAO.save(newProfile);
    }

    public List<AccountProfileModel> getAllProfile() {
        return accountProfileDAO.findAll();
    }
}

