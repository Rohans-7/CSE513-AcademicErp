package com.rohan.academics.controller;

import com.rohan.academics.dto.CreateUserRequest;
import com.rohan.academics.dto.LoginRequest;
import com.rohan.academics.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminService adminservice;

    @PostMapping("auth/admin/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(adminservice.login(request));
    }

    @PostMapping("create-user")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request) {
        String response = adminservice.createUser(request);
        return ResponseEntity.ok(response);
    }
}
