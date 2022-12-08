package com.example.AngularServerSpring.Exceptions;

public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(Long id) {
    super("Could not find task " + id);
  }
}
