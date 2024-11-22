package com.rohan.academics.service;

import com.rohan.academics.entity.User;
import com.rohan.academics.records.CreateUserRequest;
import com.rohan.academics.records.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rohan.academics.repo.CustomerRepo;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CustomerRepo customerRepo;
    private final EncryptionService encryptionService;


    public String login(LoginRequest request) {
        User user=getUser(request.email());
        if(user== null){
            return "User not found";
        }
        if(!user.getRole().equals(("admin"))){
            return "Only Admin can Login";
        }
        if(!encryptionService.validates(request.password(), user.getPassword())) {
            return "Wrong Password or Email";
        }

        return "Done";
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
