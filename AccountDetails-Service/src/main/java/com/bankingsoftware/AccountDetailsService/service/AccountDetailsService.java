package com.bankingsoftware.AccountDetailsService.service;

import com.bankingsoftware.AccountDetailsService.Model.AccountDetailsModel;
import com.bankingsoftware.AccountDetailsService.Model.ConfirmationToken;
import com.bankingsoftware.AccountDetailsService.repository.AccountDetailsDAO;
import com.bankingsoftware.AccountDetailsService.repository.ConfirmationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
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

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

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

    public ResponseEntity<String> sendEmail(String email)
    {
        AccountDetailsModel existingUser = getUserDetailsByEmail(email);
        HttpHeaders responseHeaders = new HttpHeaders();
        if (existingUser != null) {
            // Create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);

            // Save it
            confirmationTokenRepository.save(confirmationToken);

            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("tjava83@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:9000/users/confirm-reset?token=" + confirmationToken.getConfirmationToken());
            // Send the email
            emailSenderService.sendEmail(mailMessage);
            responseHeaders.set("confirmation-token",confirmationToken.getConfirmationToken());
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
            }
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body("mail sent successfully");
    }

    public ResponseEntity<String> validateToken(String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            AccountDetailsModel accountDetails = getUserDetailsByEmail(token.getAccountDetails().getEmail());
            accountDetails.setEnabled(true);
            addAccount(accountDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mail id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Confirm reset?");
    }

    public ResponseEntity<String> resetUserPassword(String email, String password) {
        if (email != null) {
            // use email to find user
            AccountDetailsModel tokenUser = getUserDetailsByEmail(email);
            tokenUser.setEnabled(true);
            tokenUser.setPassword(bCryptPasswordEncoder.encode(password));
            accountDetailsDAO.save(tokenUser);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mail id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body("password reset successfully");
    }
}
