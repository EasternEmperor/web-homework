package com.example.exp4.Dao;

import com.example.exp4.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
    boolean existsByTelephone(String telephone);
    boolean existsByEmail(String email);
    boolean existsByName(String Username);
}