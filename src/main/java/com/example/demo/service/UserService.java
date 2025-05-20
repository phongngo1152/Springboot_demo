package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    List<User> findByNameUserContaining(String nameUser);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Long id);
    List<User> searchUsers(String name, Integer gender, String birthdate, String address, Integer age);
    Page<User> getUsers(String name, Integer gender, String birthdate, String address, Integer age,Pageable pageable);
}
