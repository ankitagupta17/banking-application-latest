package com.bankingsoftware.AccountDetailsService.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    @OneToOne(targetEntity = AccountDetailsModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_no")
    private AccountDetailsModel accountDetails;

    public ConfirmationToken() {
    }

    public ConfirmationToken(AccountDetailsModel accountDetails) {
        this.accountDetails = accountDetails;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
