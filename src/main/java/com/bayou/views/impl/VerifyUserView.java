package com.bayou.views.impl;

import com.bayou.types.UserType;

/**
 * Created by Rachel on 2/21/2017.
 */
public class VerifyUserView extends UserView {

    private String enteredPasswordHash;
    private Integer enteredVerificationCode;

    public String getEnteredPasswordHash() {
        return enteredPasswordHash;
    }

    public void setEnteredPasswordHash(String enteredPasswordHash) {
        this.enteredPasswordHash = enteredPasswordHash;
    }

    public Integer getEnteredVerificationCode() {
        return enteredVerificationCode;
    }

    public void setEnteredVerificationCode(Integer enteredVerificationCode) {
        this.enteredVerificationCode = enteredVerificationCode;
    }

    public boolean login() {
        if(enteredPasswordHash == null || getPasswordHash()== null) {
            return false;
        }
        return enteredPasswordHash.equals(getPasswordHash());
    }

    public LoginView convertToLoginView() {
        LoginView loginView = new LoginView();
        loginView.setAccountName(getAccountName());
        loginView.setEmail(getEmail());
        return loginView;
    }

}
