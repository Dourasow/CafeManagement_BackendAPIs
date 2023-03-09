package com.cafemangementsystem.repository;

import com.cafemangementsystem.model.User;
import com.cafemangementsystem.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<UserWrapper> getAllUsers();
}
