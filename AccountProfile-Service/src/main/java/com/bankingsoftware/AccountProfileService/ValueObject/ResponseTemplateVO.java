package com.bankingsoftware.AccountProfileService.ValueObject;

import com.bankingsoftware.AccountProfileService.Model.AccountProfileModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {

    private AccountDetailsModel accountDetailsModel;
    private Optional<AccountProfileModel> accountProfileModel;

    public AccountDetailsModel getAccountDetailsModel() {
        return accountDetailsModel;
    }

    public void setAccountDetailsModel(AccountDetailsModel accountDetailsModel) {
        this.accountDetailsModel = accountDetailsModel;
    }

    public Optional<AccountProfileModel> getAccountProfileModel() {
        return accountProfileModel;
    }

    public void setAccountProfileModel(Optional<AccountProfileModel> accountProfileModel) {
        this.accountProfileModel = accountProfileModel;
    }
}
