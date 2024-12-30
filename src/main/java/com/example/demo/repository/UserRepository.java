package com.example.demo.repository;


import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByUsername(String username);
   Optional<User> findById(long id);
   @Query("SELECT new com.example.demo.model.UserDTO(u.id, u.username) FROM User u")
   List<UserDTO> findAllUsersWithoutPassword();
   @Query("SELECT new com.example.demo.model.UserDTO(u.id, u.username) FROM User u " +
           "WHERE u.username <> :username " +
           "AND NOT EXISTS (" +
           "    SELECT sl FROM u.participants sl WHERE sl.id = :listId" +
           ")"
             )
   List<UserDTO> findAllNonParticipantUsersForAlist(Long listId, String username);

}


