package com.toolwork.api.jpront.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toolwork.api.jpront.dtos.AuthResponse;
import com.toolwork.api.jpront.dtos.UserRequest;
import com.toolwork.api.jpront.dtos.UserResponse;
import com.toolwork.api.jpront.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AuthService authSvc;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> insertUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(authSvc.register(request));

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(authSvc.getAllUsers());
    }
}
