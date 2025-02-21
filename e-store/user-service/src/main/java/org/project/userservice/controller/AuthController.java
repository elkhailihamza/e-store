package org.project.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.project.userservice.dto.LoginRequest;
import org.project.userservice.dto.RegisterRequest;
import org.project.userservice.service.AuthService;
import org.project.userservice.vm.AuthResponseVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseVM> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


}
