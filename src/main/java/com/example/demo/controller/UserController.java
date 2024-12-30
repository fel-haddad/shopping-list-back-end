package com.example.demo.controller;

import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAllUsersWithoutPassword();
    }

    @GetMapping("/{listId}")
    public List<UserDTO> getAllNonParticipantUsers(@PathVariable Long listId) {
        return userService.findAllNonParticipantUsersForAlist(listId);
    }
}
