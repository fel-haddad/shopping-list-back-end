package com.example.demo.service;

import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import com.example.demo.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private UserService userService;

    public List<ShoppingList> getShoppingListsForUser(String username) {
        User user = userService.findByUsername(username);
        return shoppingListRepository.findByUser(user);
    }

    public Optional<ShoppingList> getShoppingListById(Long listenID){
        return shoppingListRepository.findById(listenID);
    }

    public ShoppingList createShoppingList(String username, ShoppingList shoppingList) {
        User user = userService.findByUsername(username);
        shoppingList.setUser(user);
        return shoppingListRepository.save(shoppingList);
    }

    public void deleteById(Long id) {
        shoppingListRepository.deleteById(id);
    }
}

