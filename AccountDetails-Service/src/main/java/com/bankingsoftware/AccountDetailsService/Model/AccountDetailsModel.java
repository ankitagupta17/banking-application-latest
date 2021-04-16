package com.bankingsoftware.AccountDetailsService.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDetailsModel {

    @Id
    private String account_no;
    private String firstname;
    private String lastname;
    private String email;
    private long contact;
    private String password;
}
