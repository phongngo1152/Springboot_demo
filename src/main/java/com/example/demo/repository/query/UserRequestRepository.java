package com.example.demo.repository.query;

import com.example.demo.entity.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRequestRepository extends JpaRepository<UserRequestModel, Long> {
    List<UserRequestModel> findByNameUserContaining(String userName);
    UserRequestModel findUserByNameUser(String userName);

}
