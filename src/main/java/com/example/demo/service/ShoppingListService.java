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
        return shoppingListRepository.findByOwnerAndParticipants(user.getId());
    }

    public Optional<ShoppingList> getShoppingListById(Long listenID){
        return shoppingListRepository.findById(listenID);
    }

    public ShoppingList createShoppingList(String username, ShoppingList shoppingList) {
        User user = userService.findByUsername(username);
        shoppingList.setOwner(user);
        return shoppingListRepository.save(shoppingList);
    }

    public void deleteById(Long id) {
        shoppingListRepository.deleteById(id);
    }
    // Füge einen Benutzer zu den Beteiligten hinzu
    public ShoppingList addParticipant(Long listId, Long userId) {
        Optional<ShoppingList> listOpt = shoppingListRepository.findById(listId);
        Optional<User> userOpt = userService.findById(userId);

        if (listOpt.isEmpty() || userOpt.isEmpty()) {
            throw new RuntimeException("Shopping list or User not found");
        }

        ShoppingList list = listOpt.get();
        List<User> participants = list.getParticipants();
        participants.add(userOpt.get());
        list.setParticipants(participants);

        return shoppingListRepository.save(list);
    }
    public ShoppingList addParticipantsToList(Long listId, List<Long> userIds) {
        // Bestehende Liste finden
        ShoppingList shoppingList = shoppingListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Liste nicht gefunden"));
        // Nutzer basierend auf den IDs abrufen
        List<User> usersToAdd = userService.findAllByIds(userIds);
        // Teilnehmer zur Liste hinzufügen
        shoppingList.getParticipants().addAll(usersToAdd);
        // Liste speichern
        return shoppingListRepository.save(shoppingList);
    }

}

