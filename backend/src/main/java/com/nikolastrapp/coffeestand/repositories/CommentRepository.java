package com.nikolastrapp.coffeestand.repositories;

import com.nikolastrapp.coffeestand.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}