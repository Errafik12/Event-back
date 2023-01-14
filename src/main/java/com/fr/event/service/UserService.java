package com.fr.event.service;

import com.fr.event.domain.User;
import com.fr.event.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ApiResponse> createUser(User user);

    ResponseEntity<ApiResponse> getUserByName(String name);

    ResponseEntity<ApiResponse> updateUserRole(String name, String role);
}
