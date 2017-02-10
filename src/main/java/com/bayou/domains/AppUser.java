package com.bayou.domains;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Entity
public class AppUser {


  @Id //specifies that this is the primary id of this entity
  @GeneratedValue(strategy = GenerationType.AUTO) //this ensures that a id is auto generated
  private long userID;

  @Column
  private String username;
  @Column
  private String firstName;
  @Column
  private String lastName;


  public long getUserID() {
    return userID;
  }

  public void setUserID(long userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username){
    this.username = username;
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

}
