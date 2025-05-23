package com.example.demo.service.Impl;

import com.example.demo.dto.UserEvent;
import com.example.demo.entity.UserRequestModel;
import com.example.demo.repository.query.UserRequestRepository;
import com.example.demo.service.Dao.UserRequestService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRequestImpl implements UserRequestService {
    @Autowired
    private UserRequestRepository userRequestRepository;
    private final RabbitTemplate rabbitTemplate;

    public UserRequestImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<UserRequestModel> getAllUsers() {

        List<UserRequestModel> list = userRequestRepository.findAll();
        return list;
    }

    @Override
    public UserRequestModel getUserById(Long id) {
        return userRequestRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRequestModel> findByNameUserContaining(String nameUser) {
        return userRequestRepository.findByNameUserContaining(nameUser);
    }
//
//    @RabbitListener(queues = "UserRequestModel.queue")
//    public void receiveUserEvent(UserEvent event) {
//        if (event == null) {
//            System.err.println("Received null event!");
//            return;
//        }
//
//
//        UserRequestModel user = new UserRequestModel();
//        user.setId(event.getId());
//        user.setNameUser(event.getNameUser());
//        user.setAge(event.getAge());
//        user.setGender(event.getGender());
//        user.setAddress(event.getAddress());
//        user.setBirthdate(event.getBirthdate());
//
//        try {
//            userRequestRepository.save(user);
//        } catch (ObjectOptimisticLockingFailureException e) {
//            System.out.println("Xung đột dữ liệu khi xử lý sự kiện user: {}" + event.getId());
//        }
//     }

}
