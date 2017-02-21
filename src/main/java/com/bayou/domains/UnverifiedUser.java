package com.bayou.domains;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@Entity(name = "UnverifiedUser")
@Table(name = "unverified_users")
@AttributeOverride(name = "id", column = @Column(name = "unverified_user_id"))
public class UnverifiedUser extends BaseEntity {
    @Column(name = "password_hash", columnDefinition = "VARCHAR")
    private String passwordHash;

    @Column(name = "password_salt", columnDefinition = "VARCHAR")
    private String passwordSalt;

    @Column(name = "email", columnDefinition = "VARCHAR")
    private String email;

    @Column(name = "verification_code", columnDefinition = "integer")
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

}
