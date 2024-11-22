package com.rohan.academics.controller;

import com.rohan.academics.records.CreateUserRequest;
import com.rohan.academics.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminservice;

    @PostMapping("auth/admin/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody){
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        return ResponseEntity.ok(adminservice.login(email,password));
    }

    @PostMapping("create-user")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request) {
        String response = adminservice.createUser(request);
        return ResponseEntity.ok(response);
    }
}
