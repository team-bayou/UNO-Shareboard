package com.bayou.views.impl;

import com.bayou.views.IView;

/**
 * Created by rachelguillory 2/16/2017.
 */
public class UnverifiedUserView implements IView {

    private Long id;
    private String passwordHash;
    private String passwordSalt;
    private String email;
    private Integer verificationCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerification_code(Integer verification_code) {
        this.verificationCode = verification_code;
    }

    //TODO: the following methods may need to have case by case basis of implementations
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
