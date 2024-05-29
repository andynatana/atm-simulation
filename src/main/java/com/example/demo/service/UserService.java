package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public User login(String mail, String password) {
        return userRepository.findUserByMailAndPassword(mail, password)
                .orElseThrow(() -> new UserNotFoundException("Incorrect credentials, please verify !"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
