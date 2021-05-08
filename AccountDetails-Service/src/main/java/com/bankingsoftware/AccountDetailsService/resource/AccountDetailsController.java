package com.bankingsoftware.AccountDetailsService.resource;

import com.bankingsoftware.AccountDetailsService.Model.AccountDetailsModel;
import com.bankingsoftware.AccountDetailsService.service.AccountDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class AccountDetailsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment environment;

    @Autowired
    private AccountDetailsService accountDetailsService;


    @GetMapping("/{accountNumber}")
    public Optional<AccountDetailsModel> getAccountDetails(@PathVariable String accountNumber)
    {
        return accountDetailsService.getAccountDetails(accountNumber);
    }


    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<String> createAccount(@RequestBody AccountDetailsModel accountDetailsModel)
    {
        logger.info("Create Account Controller--->");
        accountDetailsService.addAccount(accountDetailsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    // Receive the address and send an email
    @PostMapping(value="/forgot-password/{email}")
    public ResponseEntity<String> forgotUserPassword(@PathVariable String email) {
        logger.info("Forget password----->");
        return accountDetailsService.sendEmail(email);
    }

    // Endpoint to confirm the token
    @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> validateResetToken(@RequestParam("token")String confirmationToken) {
        return accountDetailsService.validateToken(confirmationToken);
    }


    // Receive the token from the link sent via email and display form to reset password
    @PutMapping(value = "/reset-password/{email}/{pass}")
    public ResponseEntity<String> resetUserPassword(@PathVariable("email") String email, @PathVariable("pass") String password) {
       return accountDetailsService.resetUserPassword(email, password);
    }
}
