package com.nikolastrapp.coffeestand.services;

import com.nikolastrapp.coffeestand.entities.User;
import com.nikolastrapp.coffeestand.entities.enums.Role;
import com.nikolastrapp.coffeestand.repositories.UserRepository;
import com.nikolastrapp.coffeestand.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> queryAll() {
        return repository.findAll();
    }

    public User queryById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // Criptografar
        user.setRole(Role.USER); // Definir a permissão padrão
        return repository.save(user);

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User user) {
        User entity = repository.getReferenceById(id);
        updateData(entity, user);
        return repository.save(entity);
    }

    private void updateData(User entity, User user) {
        if (!user.getName().isBlank()) {
            entity.setName(user.getName());
        }
        if (!user.getEmail().isBlank()) {
            entity.setEmail(user.getEmail());
        }
        if (!user.getPassword().isBlank()) {
            entity.setPassword(encoder.encode(user.getPassword()));
        }
    }

}
