package com.bayou.views.impl;

import com.bayou.views.IView;

/**
 * Created by joshuaeaton on 2/17/17.
 */
public class LoginView implements IView {
    private Long id;
    private String email;
    private String accountName;
    private String passwordSalt;
    private String passwordHash;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String account_name) {
        this.accountName = account_name;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginView loginView = (LoginView) o;

        if (id != null ? !id.equals(loginView.id) : loginView.id != null) return false;
        if (email != null ? !email.equals(loginView.email) : loginView.email != null) return false;
        if (accountName != null ? !accountName.equals(loginView.accountName) : loginView.accountName != null)
            return false;
        if (passwordSalt != null ? !passwordSalt.equals(loginView.passwordSalt) : loginView.passwordSalt != null)
            return false;
        return passwordHash != null ? passwordHash.equals(loginView.passwordHash) : loginView.passwordHash == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
        result = 31 * result + (passwordSalt != null ? passwordSalt.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        return result;
    }
}
