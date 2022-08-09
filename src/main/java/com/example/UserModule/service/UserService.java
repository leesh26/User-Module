package com.example.UserModule.service;

import com.example.UserModule.entity.User;
import com.example.UserModule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findUser(String email){
        return userRepository.findByEmail(email);
    }
}
