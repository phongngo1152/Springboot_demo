package com.example.demo.service.Impl;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.UserEvent;
import com.example.demo.dto.DTOcreateUserRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.command.ProductRepository;
import com.example.demo.repository.command.UserRepository;
import com.example.demo.service.Dao.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    private RabbitTemplate rabbitTemplate;



    public static final String EXCHANGE = "UserRequestModel.exchange";
    public static final String ROUTING_KEY = "UserRequestModel.routingkey";
    public static final String ROUTING_DELETE = "user.delete";
    public static final String ROUTING_Transactional = "UserRequestModel.Transactional";



    public UserImpl(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(new org.springframework.amqp.support.converter.Jackson2JsonMessageConverter());

    }

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
    if (user == null) {

        return false;
    }
        try {
            User savedUser = userRepository.save(user);
            UserEvent event = new UserEvent(
                    savedUser.getId(),
                    savedUser.getNameUser(),
                    savedUser.getAge(),
                    savedUser.getGender(),
                    savedUser.getAddress(),
                    savedUser.getBirthdate()

            );
            System.out.println("Added User 2: "+savedUser.toString());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Loi k save duoc");

            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null) {
            return false;
        }
        try {
            User savedUser = userRepository.save(user);
            UserEvent event = new UserEvent(
                    savedUser.getId(),
                    savedUser.getNameUser(),
                    savedUser.getAge(),
                    savedUser.getGender(),
                    savedUser.getAddress(),
                    savedUser.getBirthdate()
            );

            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, event);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            Optional<User> event = userRepository.findById(id);
            if (event.isPresent()) {
                userRepository.deleteById(id);
                rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_DELETE, event);
            }else {
                System.out.println("User không tồn tại, không thể xóa hoặc gửi event.");
            }

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

    @Override
    public Page<User> getUsersforSqlnative(String name, Integer gender, String birthdate, String address, Integer age, Pageable pageable) {
        return userRepository.searchAllBySqlnative(name,gender,birthdate,address,age,pageable);
    }
    @Transactional("writeTransactionManager")
    @Override
    public void createUserWithProduct(DTOcreateUserRequest request) {
        // Kiểm tra tên
        if (request.getNameUser() == null || request.getNameUser().trim().isEmpty()) {
            throw new RuntimeException("Tên người dùng không được để trống");
        }
        // Kiểm tra tuổi
        if (request.getAge() < 0) {
            throw new RuntimeException("Tuổi không hợp lệ");
        }
        // Kiểm tra giới tính
        if (request.getGender() != 0 && request.getGender() != 1) {
            throw new RuntimeException("Giới tính không được để trống");
        }
        // Kiểm tra địa chỉ
        if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
            throw new RuntimeException("Địa chỉ không được để trống");
        }
        // Kiểm tra ngày sinh
        if (request.getBirthdate() == null || request.getBirthdate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Ngày sinh không hợp lệ");
        }

        // Thực hiện tạo User
        User newUser = new User();
        newUser.setNameUser(request.getNameUser());
        newUser.setAge(request.getAge());
        newUser.setGender(request.getGender());
        newUser.setAddress(request.getAddress());
        newUser.setBirthdate(request.getBirthdate());
        newUser = userRepository.save(newUser);
        request.setId(newUser.getId());
        // Tạo Product
        for (String productName : request.getProductNames()) {
            Product newProduct = new Product();
            newProduct.setProductName(productName);
            newProduct.setUser(newUser);
            newProduct = productRepository.save(newProduct);
        }
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_Transactional, request);
    }



}
