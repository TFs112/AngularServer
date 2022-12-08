package com.example.AngularServerSpring.Interfaces;

import com.example.AngularServerSpring.Classes.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
