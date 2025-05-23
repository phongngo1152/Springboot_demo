package com.example.demo.repository.query;

import com.example.demo.entity.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRequestRepository extends JpaRepository<UserRequestModel, Long> {
    List<UserRequestModel> findByNameUserContaining(String userName);
    UserRequestModel findUserByNameUser(String userName);

}
