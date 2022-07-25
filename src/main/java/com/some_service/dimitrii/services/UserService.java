package com.some_service.dimitrii.services;

import com.some_service.dimitrii.entities.UserEntity;
import com.some_service.dimitrii.exeptions.ApplicationException;
import com.some_service.dimitrii.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<Optional<UserEntity>> findById(String id) {
        isUserExistById(id);
        return ResponseEntity.ok(userRepository.findById(id));
    }

    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserEntity> save(UserEntity userEntity) {
        return ResponseEntity.ok(userRepository.save(userEntity));
    }

    public ResponseEntity<UserEntity> update(String id, UserEntity userEntity) {
        UserEntity updateEmployee = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Not found user by id: " + id));

        updateEmployee.setName(userEntity.getName());

        userRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    public ResponseEntity<UserEntity> delete(String id) {
        isUserExistById(id);
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void isUserExistById(String id) {
        if (!userRepository.existsById(id)) {
            throw new ApplicationException("Not found user by id: " + id);
        }
    }
}
