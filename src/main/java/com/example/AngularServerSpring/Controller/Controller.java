package com.example.AngularServerSpring.Controller;

import com.example.AngularServerSpring.Classes.Task;
import com.example.AngularServerSpring.Classes.UserAccount;
import com.example.AngularServerSpring.Classes.UserAndPassword;
import com.example.AngularServerSpring.Exceptions.TaskNotFoundException;
import com.example.AngularServerSpring.Exceptions.UserAlreadyLoggedInException;
import com.example.AngularServerSpring.Interfaces.TaskRepository;
import com.example.AngularServerSpring.Interfaces.UserRepository;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class Controller {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  Supplier<String> tokenSupplier = () -> {

    StringBuilder token = new StringBuilder();
    long currentTimeInMillisecond = Instant.now().toEpochMilli();
    return token.append(currentTimeInMillisecond).append("-")
      .append(UUID.randomUUID().toString()).toString();
  };

  Controller(TaskRepository taskRepository, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
  }

  @GetMapping(value = "/tasks")
  List<Task> allTasks() {
    return taskRepository.findAll();
  }


  @PostMapping("/task")
  Task newTask(@RequestBody Task newTask) {
    return taskRepository.save(newTask);
  }


  @GetMapping("/tasks/{id}")
  Task one(@PathVariable Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new TaskNotFoundException(id));
  }


  @PutMapping("/replaceTask")
  Task replaceTask(@RequestBody Task newTask) {

    return taskRepository.findById(newTask.getId())
      .map(employee -> {
        employee.setName(newTask.getName());
        employee.setText(newTask.getText());
        return taskRepository.save(employee);
      })
      .orElseGet(() -> {
        newTask.setId(newTask.getId());
        return taskRepository.save(newTask);
      });
  }


  @DeleteMapping("/tasks/{id}")
  void deleteTask(@PathVariable Long id) {
    if (taskRepository.findById(id).isPresent()) {
      taskRepository.deleteById(id);
    }
  }


  @GetMapping(value = "/users")
  List<UserAccount> allUsers() {
    return userRepository.findAll();
  }


  @PostMapping("/userLogin")
  public Object login(@RequestBody UserAndPassword userAndPassword) {
    //@RequestParam(name="username") String username, @RequestParam(name="password") String pwd


    String username = userAndPassword.getUsername();
    String pwd = userAndPassword.getPassword();

    String token = tokenSupplier.get();
    UserAccount userAccount = new UserAccount();
    userAccount.setUsername(username);
    userAccount.setPassword(pwd);
    userAccount.setToken(token);
    userAccount.setActive(true);

    for (UserAccount account : userRepository.findAll()) {
      if (account.getUsername().equals(username) && !account.getToken().equals("")) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      }
      if (account.getUsername().equals(username) && !account.getPassword().equals(pwd) && account.getToken().equals("")) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

      }
      if (account.getUsername().equals(username) && account.getPassword().equals(pwd) && account.getToken().equals("")) {
        account.setToken(token);
        account.setActive(true);
        userRepository.save(account);
        return token;
      }
    }

    userRepository.save(userAccount);

    return token;
  }


  @RequestMapping(value = "/userLogout/{token}")
  public void logout(@PathVariable String token) {
    for (UserAccount account : userRepository.findAll()) {
      if (account.getToken().equals(token)) {
        account.setToken("");
        account.setActive(false);
        userRepository.save(account);
      }
    }
  }
}
