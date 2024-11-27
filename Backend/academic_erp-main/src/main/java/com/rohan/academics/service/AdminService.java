package com.rohan.academics.service;

import com.rohan.academics.dto.LoginResponse;
import com.rohan.academics.entity.User;
import com.rohan.academics.dto.CreateUserRequest;
import com.rohan.academics.dto.LoginRequest;
import com.rohan.academics.helper.EncryptionService;
import com.rohan.academics.helper.JWTHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rohan.academics.repo.CustomerRepo;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CustomerRepo customerRepo;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;


    public LoginResponse login(LoginRequest request) {
        User user = getUser(request.email());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!user.getRole().equals("admin")) {
            throw new IllegalArgumentException("Only Admin can Login");
        }

        if (!encryptionService.validates(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong Password or Email");
        }

        String token = jwtHelper.generateToken(request.email());
        return new LoginResponse(user.getUser_id(), user.getEmail(), user.getRole(), user.getName(),token);
    }

    public String createUser(CreateUserRequest request) {
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(encryptionService.encode(request.password()))
                .role(request.role())
                .build();
        customerRepo.save(user);
        return "User Created Successfully";
    }

    private User getUser( String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow();
    }
}
