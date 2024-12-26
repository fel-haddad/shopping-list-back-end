package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {
@Autowired
    UserRepository userRepository;
@GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAllUsersWithoutPassword();
    }
}
