package com.example.demo.service.Dao;


import com.example.demo.entity.UserRequestModel;

import java.util.List;

public interface UserRequestService {
    List<UserRequestModel> getAllUsers();
    UserRequestModel getUserById(Long id);
    List<UserRequestModel> findByNameUserContaining(String nameUser);
}
