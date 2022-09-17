package com.nikolastrapp.coffeestand.repositories;

import com.nikolastrapp.coffeestand.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}