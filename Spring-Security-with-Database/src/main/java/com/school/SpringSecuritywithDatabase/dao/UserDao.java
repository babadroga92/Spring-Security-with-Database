package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    String deleteById(int id);
    @Query("Select Count (u.id) from User u where u.roles = com.school.SpringSecuritywithDatabase.enums.Roles.ADMIN")
    Integer numberOfAdmins();
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.isEnabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

    List<User> findByCanBeDeleted(boolean canBeDeleted);
}
