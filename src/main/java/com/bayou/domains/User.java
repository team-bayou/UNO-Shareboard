package com.bayou.domains;


import com.bayou.types.UserType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Entity(name = "User")
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Advertisement> advertisements;

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

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
