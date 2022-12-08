package com.example.AngularServerSpring.Exceptions;

public class UserAlreadyLoggedOutException extends RuntimeException{
  public UserAlreadyLoggedOutException(String token) {
    super("User with this token : " +token+ " is already logged out.");
  }
}
