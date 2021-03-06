package com.bankingsoftware.AccountProfileService.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;



public class AccountDetailsModel {
    @Id
    private String account_no;
    private String firstname;
    private String lastname;
    private String email;
    private long contact;

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public AccountDetailsModel() {
    }

    public AccountDetailsModel(String account_no, String firstname, String lastname, String email, long contact) {
        this.account_no = account_no;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }



}
