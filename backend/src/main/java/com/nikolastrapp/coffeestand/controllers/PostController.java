package com.nikolastrapp.coffeestand.controllers;

import com.nikolastrapp.coffeestand.entities.Post;
import com.nikolastrapp.coffeestand.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> queryAll () {
        List<Post> list = service.queryAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/insertPost")
    public ResponseEntity<Post> insert (@RequestBody Post post){
        service.insert(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).body(post);
    }

    @DeleteMapping(value = "/deletePost/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/updatePost/{id}")
    public ResponseEntity<Post> update (@PathVariable Long id, @RequestBody Post post) {
        service.update(post, id);
        return ResponseEntity.ok().body(post);
    }

}

