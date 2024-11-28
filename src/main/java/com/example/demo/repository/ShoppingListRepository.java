package com.example.demo.repository;

import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    List<ShoppingList> findByUser(User user);

    Optional<ShoppingList> findByIdAndUser(Long listId, User owner);
    Optional<ShoppingList> findById(Long listId);
}

