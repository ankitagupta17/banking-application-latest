package com.bankingsoftware.AccountProfileService.repository;

import com.bankingsoftware.AccountProfileService.Model.AccountProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProfileDAO extends JpaRepository<AccountProfileModel,String> {
}
