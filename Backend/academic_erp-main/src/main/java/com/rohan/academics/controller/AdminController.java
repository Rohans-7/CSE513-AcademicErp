package com.rohan.academics.controller;

import com.rohan.academics.records.CreateUserRequest;
import com.rohan.academics.records.LoginRequest;
import com.rohan.academics.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminController {
    private final AdminService adminservice;

    @PostMapping("auth/admin/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(adminservice.login(request));
    }

    @PostMapping("create-user")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request) {
        String response = adminservice.createUser(request);
        return ResponseEntity.ok(response);
    }
}
