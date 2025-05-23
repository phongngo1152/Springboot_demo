package com.example.demo.controller;

import com.example.demo.entity.UserRequestModel;
import com.example.demo.service.Dao.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userrequest")
public class UserRequestController {
    @Autowired
    private UserRequestService userRequestService;

    @GetMapping("/")
    public List<UserRequestModel> getUsers() {
        return userRequestService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserRequestModel getUser(Long id) {
        UserRequestModel userRequestModel = userRequestService.getUserById(id);
        return userRequestModel;
    }

}
