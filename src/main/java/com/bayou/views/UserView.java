package com.bayou.views;

import com.bayou.types.UserType;

/**
 * Created by joshuaeaton on 1/31/17.
 */
public class UserView extends BaseEntityView {
    private String accountName;
    private String passwordHash;
    private String passwordSalt;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String facebookId;
    private String twitterHandle;
    private Integer imageId;
    private Integer verificationCode;
    private boolean showFullName;
    private boolean showEmail;
    private boolean showPhoneNumber;

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
        return this.facebookId;
    }

    public void setFacebookId(String facebookID) {
        this.facebookId = facebookID;
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

    public boolean isShowFullName() {
        return showFullName;
    }

    public void setShowFullName(boolean showName) {
        this.showFullName = showName;
    }

    public boolean isShowEmail() {
        return showEmail;
    }

    public void setShowEmail(boolean showEmail) {
        this.showEmail = showEmail;
    }

    public boolean isShowPhoneNumber() {
        return showPhoneNumber;
    }

    public void setShowPhoneNumber(boolean showPhoneNumber) {
        this.showPhoneNumber = showPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserView userView = (UserView) o;

        if (accountName != null ? !accountName.equals(userView.accountName) : userView.accountName != null)
            return false;
        if (passwordHash != null ? !passwordHash.equals(userView.passwordHash) : userView.passwordHash != null)
            return false;
        if (passwordSalt != null ? !passwordSalt.equals(userView.passwordSalt) : userView.passwordSalt != null)
            return false;
        if (userType != userView.userType) return false;
        if (firstName != null ? !firstName.equals(userView.firstName) : userView.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userView.lastName) : userView.lastName != null) return false;
        if (email != null ? !email.equals(userView.email) : userView.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(userView.phoneNumber) : userView.phoneNumber != null)
            return false;
        if (facebookId != null ? !facebookId.equals(userView.facebookId) : userView.facebookId != null) return false;
        if (twitterHandle != null ? !twitterHandle.equals(userView.twitterHandle) : userView.twitterHandle != null)
            return false;
        return imageId != null ? imageId.equals(userView.imageId) : userView.imageId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
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
        return "UserView{" +
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
}
