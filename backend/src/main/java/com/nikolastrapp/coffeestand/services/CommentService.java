package com.nikolastrapp.coffeestand.services;

import com.nikolastrapp.coffeestand.entities.Comment;
import com.nikolastrapp.coffeestand.repositories.CommentRepository;
import com.nikolastrapp.coffeestand.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public List<Comment> queryAll() {
        return repository.findAll();
    }

    public Comment queryById(Long id) {
        Optional<Comment> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Comment insert (Comment comment) {
        return repository.save(comment);
    }

    public void delete (Long id){
        repository.deleteById(id);
    }

    public Comment update (Comment comment, Long id){
        Comment entity = repository.getReferenceById(id);
        updateData(entity, comment);
        return repository.save(entity);
    }

    private void updateData(Comment entity, Comment comment) {
        if (!comment.getContent().isBlank()) {
            entity.setContent(comment.getContent());
        }
    }
}
