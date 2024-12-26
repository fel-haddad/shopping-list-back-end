package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public Optional<User> findById(Long id) {
        return  userRepository.findById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public List<UserDTO> getAllUsers(){
        return userRepository.findAllUsersWithoutPassword();
    }
    public List<User> findAllByIds(List<Long> ids){
        return userRepository.findAllById(ids);
    }

}

