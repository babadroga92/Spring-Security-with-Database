package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    String deleteById(int id);

}
