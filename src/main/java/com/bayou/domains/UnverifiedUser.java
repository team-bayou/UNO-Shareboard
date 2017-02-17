package com.bayou.domains;


import javax.persistence.*;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@Entity(name = "UnverifiedUser")
@Table(name = "unverified_users")
public class UnverifiedUser {
  @Id
  @Column(name="unverified_user_id", columnDefinition = "serial")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_name", columnDefinition = "VARCHAR")
  private String accountName;

  @Column(name = "password_hash", columnDefinition = "VARCHAR")
  private String passwordHash;

  @Column(name = "password_salt", columnDefinition = "VARCHAR")
  private String passwordSalt;

  @Column(name = "email", columnDefinition = "VARCHAR")
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
