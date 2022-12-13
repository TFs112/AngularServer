package com.example.AngularServerSpring.Classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAccount {
  private @Id @GeneratedValue Long id;
  private String username;
  private String password;
  private String token;
  private boolean isActive;

  public UserAccount(){

  }

  public UserAccount(String username, String password, String token) {
    this.username = username;
    this.password = password;
    this.password = token;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    this.isActive = active;
  }
}
