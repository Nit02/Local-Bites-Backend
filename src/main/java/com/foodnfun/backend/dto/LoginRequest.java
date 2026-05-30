package com.foodnfun.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "Valid email required")
    @NotBlank
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public String getEmail()               { return email; }
    public void   setEmail(String email)   { this.email = email; }
    public String getPassword()            { return password; }
    public void   setPassword(String pw)   { this.password = pw; }
}
