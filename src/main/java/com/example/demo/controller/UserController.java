package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.Dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        boolean addUser = userService.addUser(user);
        if (addUser == true) {
            return true;
        }
        return false;
    }
    @PutMapping("/update/{id}")
    public boolean updateUser(@RequestBody User user) {
        boolean updateUser = userService.updateUser(user);
        if (updateUser == true) {
            return true;
        }
        return false;
    }
    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable Long id) {
        boolean deleteUser = userService.deleteUser(id);
        if (deleteUser == true) {
            return true;

        }
        return false;
    }
    @GetMapping("/search-by-name")
    public List<User> searchUser(@RequestParam String name) {
        return userService.findByNameUserContaining(name);
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Integer age,
                                                 @RequestParam(required = false) Integer gender,
                                                 @RequestParam(required = false) String birthdate,
                                                 @RequestParam(required = false) String address



    ) {

        return ResponseEntity.ok(userService.searchUsers(name,gender,birthdate,address,age));
    }
    @GetMapping("/search-all")
    public ResponseEntity<Page<User>> searchUsers(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Integer age,
                                                  @RequestParam(required = false) Integer gender,
                                                  @RequestParam(required = false) String birthdate,
                                                  @RequestParam(required = false) String address,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "6") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUsers(name,gender,birthdate,address,age,pageable));

    }
    @GetMapping("/search-add-sqlnative")
    public ResponseEntity<Page<User>> searchAllUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) String birthdate,
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getUsersforSqlnative(name,gender,birthdate,address,age,pageable));
    }
}
