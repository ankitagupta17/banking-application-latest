package com.bankingsoftware.AccountProfileService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class AccountProfileModel {
    @Id
    private String account_no;
    private String pin;


}
