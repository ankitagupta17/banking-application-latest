package com.bankingsoftware.AccountProfileService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class AccountProfileModel {

    public AccountProfileModel(String account_no, String pin) {
        this.account_no = account_no;
        this.pin = pin;
    }

    @Id
    private String account_no;
    private String pin;


}
