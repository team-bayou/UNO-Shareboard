package com.bayou.views.impl;

import com.bayou.types.UserType;

/**
 * Created by Rachel on 2/21/2017.
 */
public class VerifyUserView extends UserView {

    private String enteredPasswordHash;
<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
=======
    private String enteredPasswordSalt;
>>>>>>> Authentication(Login/Verification) update.
    private Integer enteredVerificationCode;

    public String getEnteredPasswordHash() {
        return enteredPasswordHash;
    }

    public void setEnteredPasswordHash(String enteredPasswordHash) {
        this.enteredPasswordHash = enteredPasswordHash;
    }

<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
=======
    public String getEnteredPasswordSalt() {
        return enteredPasswordSalt;
    }

    public void setEnteredPasswordSalt(String enteredPasswordSalt) {
        this.enteredPasswordSalt = enteredPasswordSalt;
    }

>>>>>>> Authentication(Login/Verification) update.
    public Integer getEnteredVerificationCode() {
        return enteredVerificationCode;
    }

    public void setEnteredVerificationCode(Integer enteredVerificationCode) {
        this.enteredVerificationCode = enteredVerificationCode;
    }

    public boolean login() {
<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
        if(enteredPasswordHash == null || getPasswordHash()== null) {
            return false;
        }
        return enteredPasswordHash.equals(getPasswordHash());
=======
        if(enteredPasswordHash == null || enteredPasswordSalt == null ||
              getPasswordHash()== null || getPasswordSalt()   == null) {
            return false;
        }
        return enteredPasswordHash.equals(getPasswordHash()) && enteredPasswordSalt.equals(getPasswordSalt());
>>>>>>> Authentication(Login/Verification) update.
    }

    public LoginView convertToLoginView() {
        LoginView loginView = new LoginView();
        loginView.setAccountName(getAccountName());
        loginView.setEmail(getEmail());
        return loginView;
    }

}
