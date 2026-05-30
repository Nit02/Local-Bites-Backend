package com.foodnfun.backend.controller;

import com.foodnfun.backend.dto.AuthResponse;
import com.foodnfun.backend.dto.LoginRequest;
import com.foodnfun.backend.dto.UserDTO;
import com.foodnfun.backend.response.ApiResponse;
import com.foodnfun.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Register and Login endpoints")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @Valid @RequestBody UserDTO userDTO) {

        AuthResponse auth = userService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User registered successfully", auth));
    }

    @PostMapping("/login")
    @Operation(summary = "Login and get JWT token")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse auth = userService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", auth));
    }
}
