package com.nikolastrapp.coffeestand.repositories;

import com.nikolastrapp.coffeestand.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
