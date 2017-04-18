package com.bayou.views;

/**
 * Created by rachelguillory 2/16/2017.
 */
public class UnverifiedUserView extends BaseEntityView {
    private String passwordHash;
    private String passwordSalt;
    private String email;
    private Integer verificationCode;

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

    public void setVerificationCode(Integer verification_code) {
        this.verificationCode = verification_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnverifiedUserView that = (UnverifiedUserView) o;

        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;
        if (passwordSalt != null ? !passwordSalt.equals(that.passwordSalt) : that.passwordSalt != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return verificationCode != null ? verificationCode.equals(that.verificationCode) : that.verificationCode == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (passwordSalt != null ? passwordSalt.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (verificationCode != null ? verificationCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UnverifiedUserView{" +
                "passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", email='" + email + '\'' +
                ", verificationCode=" + verificationCode +
                "} " + super.toString();
    }
}
