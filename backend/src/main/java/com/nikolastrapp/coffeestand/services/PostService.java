package com.nikolastrapp.coffeestand.services;

import com.nikolastrapp.coffeestand.entities.Post;
import com.nikolastrapp.coffeestand.entities.User;
import com.nikolastrapp.coffeestand.repositories.PostRepository;
import com.nikolastrapp.coffeestand.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> queryAll() {
        return repository.findAll();
    }

    public Post queryById(Long id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Post insert (Post post) {
        return repository.save(post);
    }

    public void delete (Long id){
        repository.deleteById(id);
    }

    public Post update (Post post, Long id){
        Post entity = repository.getReferenceById(id);
        updateData(entity, post);
        return repository.save(entity);
    }

    private void updateData(Post entity, Post post) {
        if (!post.getTitle().isBlank()) {
            entity.setTitle(post.getTitle());
        }
        if (!post.getContent().isBlank()) {
            entity.setContent(post.getContent());
        }
    }
}
