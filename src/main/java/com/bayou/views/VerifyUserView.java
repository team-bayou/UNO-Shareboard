package com.bayou.views;

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

    public Integer getEnteredVerificationCode() {
        return enteredVerificationCode;
    }

    public void setEnteredVerificationCode(Integer enteredVerificationCode) {
        this.enteredVerificationCode = enteredVerificationCode;
    }

    public String getEnteredPasswordSalt() {
        return enteredPasswordSalt;
    }

    public void setEnteredPasswordSalt(String enteredPasswordSalt) {
        this.enteredPasswordSalt = enteredPasswordSalt;
    }

    public boolean login() {
        if (enteredPasswordHash == null || getPasswordHash() == null) {
            return false;
        }
        return enteredPasswordHash.equals(getPasswordHash());
    }

    @Override
    public String toString() {
        return "VerifyUserView{" +
                "enteredPasswordHash='" + enteredPasswordHash + '\'' +
                ", enteredPasswordSalt='" + enteredPasswordSalt + '\'' +
                ", enteredVerificationCode=" + enteredVerificationCode +
                "} " + super.toString();
    }
}
