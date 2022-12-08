package com.example.AngularServerSpring.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Task {

  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
  private String name;
  private String text;

  @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
  private LocalDateTime created_at;

  Task(){

  }

  public Task(String name, String text){
    this.name = name;
    this.text = text;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.text);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Task))
      return false;
    Task task = (Task) obj;
    return Objects.equals(this.id, task.id) && Objects.equals(this.name, task.name)
      && Objects.equals(this.text, task.text);
  }

  @Override
  public String toString() {
    return "Task{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", text='" + text + '\'' +
      '}';
  }
}
