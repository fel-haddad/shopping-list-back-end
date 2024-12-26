package com.example.demo.repository;

import com.example.demo.model.ShoppingList;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    List<ShoppingList> findByOwner(User owner);
    Optional<ShoppingList> findById(Long listId);
    @Query("""
    SELECT DISTINCT sl 
    FROM ShoppingList sl 
    LEFT JOIN sl.participants p
    WHERE sl.owner.id = :userId OR p.id = :userId
""")
    List<ShoppingList> findByOwnerAndParticipants(Long userId);
}

