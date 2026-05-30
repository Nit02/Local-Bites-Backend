package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.AuthResponse;
import com.foodnfun.backend.dto.LoginRequest;
import com.foodnfun.backend.dto.UserDTO;

import java.util.List;

public interface UserService {
    AuthResponse register(UserDTO userDTO);
    AuthResponse login(LoginRequest request);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
