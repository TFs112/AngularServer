package com.example.AngularServerSpring.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAlreadyLoggedInAdvice {

  @ResponseBody
  @ExceptionHandler(UserAlreadyLoggedInException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userAlreadyLoggedInHandler(UserAlreadyLoggedInException ex) {
    return ex.getMessage();
  }
}
