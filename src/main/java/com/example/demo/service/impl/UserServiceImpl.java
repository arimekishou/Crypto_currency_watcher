package com.example.demo.service.impl;

import com.example.demo.entities.AppUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }
}
