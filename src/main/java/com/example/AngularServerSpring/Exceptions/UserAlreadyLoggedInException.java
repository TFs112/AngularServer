package com.example.AngularServerSpring.Exceptions;

public class UserAlreadyLoggedInException extends RuntimeException {

  public UserAlreadyLoggedInException(String name) {
    super("This user : " +name+ " is already logged in.");
  }
}
