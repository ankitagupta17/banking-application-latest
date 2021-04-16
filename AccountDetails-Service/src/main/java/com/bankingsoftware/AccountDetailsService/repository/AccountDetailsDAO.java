package com.bankingsoftware.AccountDetailsService.repository;

import com.bankingsoftware.AccountDetailsService.Model.AccountDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsDAO extends JpaRepository<AccountDetailsModel,String> {
    AccountDetailsModel findByEmail(String email);
}
