package com.bankingsoftware.AccountDetailsService.repository;

import com.bankingsoftware.AccountDetailsService.Model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}