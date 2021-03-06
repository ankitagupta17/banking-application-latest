package com.bankingsoftware.AccountProfileService.Exception;

public class AccountDetailsNotFoundException extends RuntimeException{
    private final String account_no;

    public AccountDetailsNotFoundException(final String account_no) {
        super("There does not exist a user with account number "+account_no);
        this.account_no = account_no;
    }
}
