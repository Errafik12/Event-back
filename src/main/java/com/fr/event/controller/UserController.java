package com.fr.event.controller;

import com.fr.event.domain.User;
import com.fr.event.service.UserService;
import com.fr.event.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<ApiResponse> getUserByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @PutMapping("/updateRole/{name}/{role}")
    public ResponseEntity<ApiResponse> updateUserRole(@PathVariable("name") String name, @PathVariable("role") String role) {
        return userService.updateUserRole(name, role);
    }
}
