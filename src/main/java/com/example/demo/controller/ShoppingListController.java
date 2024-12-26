package com.example.demo.controller;

import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ShoppingListService;
import com.example.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/shopping-lists")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private  UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<ShoppingList>> getUserShoppingLists() {
        String username = JwtTokenUtil.getCurrentUsername(); // Extract username from JWT

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // No valid token
        }

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Get shopping lists belonging to the user
        List<ShoppingList> shoppingLists = shoppingListService.getShoppingListsForUser(user.getUsername());

        return ResponseEntity.ok(shoppingLists);
    }

    @PostMapping
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList, Principal principal) {
        String username = principal.getName();
        return shoppingListService.createShoppingList(username, shoppingList);
    }
    @PostMapping("/share/{listId}")
    public ShoppingList addParticipant(@PathVariable Long listId,  @RequestBody List<Long> userIds) {

        return shoppingListService.addParticipantsToList(listId, userIds);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        shoppingListService.deleteById(id);
    }
}

