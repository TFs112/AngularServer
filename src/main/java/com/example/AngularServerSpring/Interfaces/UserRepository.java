package com.example.AngularServerSpring.Interfaces;

import com.example.AngularServerSpring.Classes.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserAccount,Long> {
}
