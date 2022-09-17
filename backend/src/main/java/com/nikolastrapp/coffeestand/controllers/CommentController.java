package com.nikolastrapp.coffeestand.controllers;

import com.nikolastrapp.coffeestand.entities.Comment;
import com.nikolastrapp.coffeestand.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<List<Comment>> queryAll () {
        List<Comment> list = service.queryAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/insertComment")
    public ResponseEntity<Comment> insert (@RequestBody Comment comment){
        service.insert(comment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId())
                .toUri();
        return ResponseEntity.created(uri).body(comment);
    }

    @DeleteMapping(value = "/deleteComment/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/updateComment/{id}")
    public ResponseEntity<Comment> update (@PathVariable Long id, @RequestBody Comment comment) {
        service.update(comment, id);
        return ResponseEntity.ok().body(comment);
    }

}

