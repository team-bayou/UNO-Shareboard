package com.bayou.views.impl;

import com.bayou.views.IView;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by joshuaeaton on 1/31/17.
 */
public class UserView implements IView {


    private long user_id;
    private String account_name;
    private String password_hash;
    private String password_salt;
    private String first_Name;
    private String last_Name;
    private String email;
    private String phone_number;
    private String facebook_id;
    private String twitter_handle;
  //  private Integer image_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFacebook_id() {
        return this.facebook_id;
    }

    public void setFacebook_id(String facebookID) {
        this.facebook_id = facebookID;
    }

    public String getTwitter_handle() {
        return twitter_handle;
    }

    public void setTwitter_handle(String twitterHandle) {
        this.twitter_handle = twitterHandle;
    }

   // public Integer getImage_id() {
        //return image_id;
  //  }

   // public void setImage_id(Integer image_id) {
     //   this.image_id = image_id;
   // }


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
