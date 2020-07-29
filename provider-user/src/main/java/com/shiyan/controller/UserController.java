package com.shiyan.controller;

import com.shiyan.dto.User;
import com.shiyan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id){
        return this.userRepository.findById(id);
    }

    @GetMapping
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}