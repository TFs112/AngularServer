package com.example.AngularServerSpring.Exceptions;

public class WrongCredentialsException extends RuntimeException{
  public WrongCredentialsException(String name) {
    super("Wrong credentials for user : " +name);
  }
}
