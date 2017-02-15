package com.bayou.domains;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Table(name = "users")
@Entity(name = "User")
public class User {


  @javax.persistence.Id //specifies that this is the primary id of this entity
  //@GeneratedValue(strategy = GenerationType.AUTO) //this ensures that a id is auto generated
  private long user_id;

  @Column(name = "account_name", columnDefinition = "VARCHAR")
  private String account_name;
  @Column(name = "password_hash", columnDefinition = "VARCHAR")
  private String password_hash;
  @Column(name = "password_salt", columnDefinition = "VARCHAR")
  private String password_salt;
  @Column(name = "first_name", columnDefinition = "VARCHAR")
  private String first_Name;
  @Column(name = "last_name", columnDefinition = "VARCHAR")
  private String last_Name;
  @Column(name = "email", columnDefinition = "VARCHAR")
  private String email;
  @Column(name = "phone_number", columnDefinition = "VARCHAR")
  private String phone_number;
  @Column(name = "facebook_id", columnDefinition = "VARCHAR")
  private String facebook_id;
  @Column(name = "twitter_handle", columnDefinition = "VARCHAR")
  private String twitter_handle;
 // @Column(name = "image_id", columnDefinition = "INTEGER")
  //private Integer image_id;


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

  public String getFacebookID() {
    return facebook_id;
  }

  public void setFacebookID(String facebookID) {
    this.facebook_id = facebook_id;
  }

  public String getTwitterHandle() {
    return twitter_handle;
  }

  public void setTwitterHandle(String twitterHandle) {
    this.twitter_handle = twitterHandle;
  }





}
