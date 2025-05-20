package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByNameUserContaining(String nameUser) {
        return userRepository.findByNameUserContaining(nameUser);
    }

    @Override
    public boolean addUser(User user) {
        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<User> searchUsers(String name, Integer gender, String birthdate, String address, Integer age) {
        return userRepository.searchUserBy(name ,gender,birthdate,address,age);
    }

    @Override
    public Page<User> getUsers(String name, Integer gender, String birthdate, String address, Integer age, Pageable pageable) {
        return userRepository.searchbyAll(name,gender,birthdate,address,age,pageable);
    }


}
