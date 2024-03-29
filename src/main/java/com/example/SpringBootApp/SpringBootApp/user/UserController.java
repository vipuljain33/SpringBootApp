package com.example.SpringBootApp.SpringBootApp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

        User user =  service.findOne(id);

        if(user == null)
        {
            throw new UserNotFoundException("id ="+ id);
        }

        return user;
    }

    //Created
    //input details of a user
    //output - created and uri of created user

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user = service.deleteById(id);

        if(user == null)
        {
            throw new UserNotFoundException("id = "+ id);
        }

    }


}
