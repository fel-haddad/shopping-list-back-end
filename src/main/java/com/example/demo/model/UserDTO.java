package com.example.demo.model;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private Long id;
    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
