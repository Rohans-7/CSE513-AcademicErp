package com.rohan.academics.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rohan.academics.entity.User;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
