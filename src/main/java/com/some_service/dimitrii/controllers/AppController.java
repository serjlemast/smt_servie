package com.some_service.dimitrii.controllers;

import com.some_service.dimitrii.entities.UserEntity;
import com.some_service.dimitrii.services.UserService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class AppController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return userService.save(userEntity);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String id,@RequestBody UserEntity userEntity){
        return userService.update(id,userEntity);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable String id){
        return userService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleError(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
