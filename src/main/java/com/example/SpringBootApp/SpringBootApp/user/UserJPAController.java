package com.example.SpringBootApp.SpringBootApp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserJPAController {


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
