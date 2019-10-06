package com.example.SpringBootApp.SpringBootApp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;


    //GET /users
    //retrieve all users

    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
        return service.findAll();
    }

    //GET /users/{id}
    //retrieveUser(id)

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id)
    {

        return service.findOne(id);
    }

    //Created
    //input details of a user
    //output - created and uri of created user

    @PostMapping("/users")
    public void createUser(@RequestBody User user)
    {
        User savedUser = service.save(user);
    }


}
