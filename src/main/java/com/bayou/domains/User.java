package com.bayou.domains;


import com.bayou.types.UserType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Entity(name = "User")
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity implements Persistable<Long> {
    @Column(name = "account_name", columnDefinition = "VARCHAR")
    private String accountName;

    @Column(name = "password_hash", columnDefinition = "CHARACTER")
    private String passwordHash;

    @Column(name = "password_salt", columnDefinition = "CHARACTER")
    private String passwordSalt;

    @Column(name = "user_type")
    @Type(type = "com.bayou.types.PGEnumUserType", parameters = {@org.hibernate.annotations.Parameter(name = "enumClassName", value = "com.bayou.types.UserType")})
    private UserType userType;

    @Column(name = "first_name", columnDefinition = "VARCHAR")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR")
    private String lastName;

    @Column(name = "email", columnDefinition = "VARCHAR")
    private String email;

    @Column(name = "phone_number", columnDefinition = "VARCHAR")
    private String phoneNumber;

    @Column(name = "facebook_id", columnDefinition = "VARCHAR")
    private String facebookId;

    @Column(name = "twitter_handle", columnDefinition = "VARCHAR")
    private String twitterHandle;

    @Column(name = "image_id", columnDefinition = "INTEGER")
    private Integer imageId;

    @Column(name = "verification_code", columnDefinition = "INTEGER")
    private Integer verificationCode;

    @Column(name = "view_flag", columnDefinition = "INTEGER")
    private Integer view_flag;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getViewFlag() { return view_flag; }

    public void setViewFlag(Integer view_flag) { this.view_flag = view_flag; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (accountName != null ? !accountName.equals(user.accountName) : user.accountName != null) return false;
        if (passwordHash != null ? !passwordHash.equals(user.passwordHash) : user.passwordHash != null) return false;
        if (passwordSalt != null ? !passwordSalt.equals(user.passwordSalt) : user.passwordSalt != null) return false;
        if (userType != user.userType) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (facebookId != null ? !facebookId.equals(user.facebookId) : user.facebookId != null) return false;
        if (twitterHandle != null ? !twitterHandle.equals(user.twitterHandle) : user.twitterHandle != null)
            return false;
        return imageId != null ? imageId.equals(user.imageId) : user.imageId == null;
    }

    @Override
    public int hashCode() {
        int result = accountName != null ? accountName.hashCode() : 0;
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (passwordSalt != null ? passwordSalt.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        result = 31 * result + (twitterHandle != null ? twitterHandle.hashCode() : 0);
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountName='" + accountName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", userType=" + userType +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", twitterHandle='" + twitterHandle + '\'' +
                ", imageId=" + imageId +
                "} " + super.toString();
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.getId() == null;
    }
}
