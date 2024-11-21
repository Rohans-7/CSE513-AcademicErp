package com.esd.academicerp.service;

import com.esd.academicerp.dto.CreateUserRequest;
import com.esd.academicerp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.esd.academicerp.repo.UserRepository;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            System.out.println("User not found: " + email);
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        System.out.println("User found: " + user.getEmail() + " with role: " + user.getRole());

        String hashedPass=passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Password mismatch for user: " + user.getEmail());
            throw new RuntimeException("Invalid password");
        }

        if (!"admin".equalsIgnoreCase(user.getRole())) {
            System.out.println("Access denied: User role is " + user.getRole());
            throw new RuntimeException("Access denied: Only admins can log in");
        }

        return "Login successful. Welcome, " + user.getEmail() + "!";
    }

    public String createUser(CreateUserRequest request) {
        // Check if username or email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Username or email already exists");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save the user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        user.setName(request.getName());
        user.setRole(request.getRole());

        userRepository.save(user);
        return "User created successfully with email: " + user.getEmail();
    }
}

