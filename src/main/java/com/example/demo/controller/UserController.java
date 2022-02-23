package com.example.demo.controller;

import com.example.demo.entities.AppUser;
import com.example.demo.entities.Crypto;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
@RequestMapping(value = "/")
@AllArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserServiceImpl userService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveUser(String userName, Crypto crypto) {
        AppUser appUser = new AppUser();
        appUser.setUserName(userName);
        appUser.setCrypto((List<Crypto>) crypto);
        userService.saveUser(appUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
