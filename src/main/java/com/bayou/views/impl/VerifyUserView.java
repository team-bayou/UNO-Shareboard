package com.bayou.views.impl;

import com.bayou.types.UserType;

/**
 * Created by Rachel on 2/21/2017.
 */
public class VerifyUserView extends UserView {

    private String enteredPasswordHash;
    private String enteredPasswordSalt;
    private Integer enteredVerificationCode;

    public String getEnteredPasswordHash() {
        return enteredPasswordHash;
    }

    public void setEnteredPasswordHash(String enteredPasswordHash) {
        this.enteredPasswordHash = enteredPasswordHash;
    }

    public String getEnteredPasswordSalt() {
        return enteredPasswordSalt;
    }

    public void setEnteredPasswordSalt(String enteredPasswordSalt) {
        this.enteredPasswordSalt = enteredPasswordSalt;
    }

    public Integer getEnteredVerificationCode() {
        return enteredVerificationCode;
    }

    public void setEnteredVerificationCode(Integer enteredVerificationCode) {
        this.enteredVerificationCode = enteredVerificationCode;
    }

    public boolean login() {
        if(enteredPasswordHash == null || enteredPasswordSalt == null ||
              getPasswordHash()== null || getPasswordSalt()   == null) {
            return false;
        }
        return enteredPasswordHash.equals(getPasswordHash()) && enteredPasswordSalt.equals(getPasswordSalt());
    }

    public LoginView convertToLoginView() {
        LoginView loginView = new LoginView();
        loginView.setAccountName(getAccountName());
        loginView.setEmail(getEmail());
        return loginView;
    }

}
