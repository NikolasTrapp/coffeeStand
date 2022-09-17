package com.nikolastrapp.coffeestand.controllers;

import com.nikolastrapp.coffeestand.entities.User;
import com.nikolastrapp.coffeestand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> queryAll () {
        List<User> list = service.queryAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/insertUser")
    public ResponseEntity<User> insert (@RequestBody User user){
        service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> update (@PathVariable Long id, @RequestBody User user) {
        service.update(id, user);
        return ResponseEntity.ok().body(user);
    }

}
