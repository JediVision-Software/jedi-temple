package com.jedivision.dao;

import com.jedivision.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    @Query("SELECT u FROM users u WHERE u.username != ?")
    List<User> findUsersExceptCurrentUser(String username);
}
