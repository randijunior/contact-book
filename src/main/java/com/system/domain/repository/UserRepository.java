package com.system.domain.repository;

import com.system.domain.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
