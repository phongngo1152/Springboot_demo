package com.example.demo.service.queryService;

import com.example.demo.dto.UserEvent;
import com.example.demo.entity.UserRequestModel;
import com.example.demo.repository.query.UserRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserQueryService {
    @Autowired
    private UserRequestRepository userReadRepository;

    @RabbitListener(queues = "UserRequestModel.queue")
    public void receiveUserEvent(UserEvent event) {

        Optional<UserRequestModel> userOptional = userReadRepository.findById(event.getId());
        if (userOptional.isPresent()) {
            //update user
            UserRequestModel userreq = userOptional.get();

            System.out.println("userreq.getId():"+userreq.getId());

            userreq.setId(event.getId());
            userreq.setNameUser(event.getNameUser());
            userreq.setAge(event.getAge());
            userreq.setGender(event.getGender());
            userreq.setAddress(event.getAddress());
            userreq.setBirthdate(event.getBirthdate());
            try {
                userReadRepository.save(userreq);
            } catch (ObjectOptimisticLockingFailureException e) {
                log.warn("Xung đột dữ liệu khi xử lý sự kiện user update: {}", userreq.toString());
            }
        }
        else {
            UserRequestModel user = new UserRequestModel();
            user.setNameUser(event.getNameUser());
            user.setAge(event.getAge());
            user.setGender(event.getGender());
            user.setAddress(event.getAddress());
            user.setBirthdate(event.getBirthdate());
            try {
                userReadRepository.save(user);
            } catch (ObjectOptimisticLockingFailureException e) {
                log.warn("Xung đột dữ liệu khi xử lý sự kiện user create: {}", user.toString());
            }
        }



    }

    @RabbitListener(queues = "UserRequestModel.queue.delete")
    public void deleteReadUser(UserEvent event){

        try {
            // Kiểm tra null hoặc trống
            if (event.getNameUser() == null || event.getNameUser().isEmpty()) {
                System.out.println("Tên người dùng không hợp lệ");
                return;
            }

            // Tìm user theo tên
            UserRequestModel userreq = userReadRepository.findUserByNameUser(event.getNameUser());

            if (userreq != null) {
                // Kiểm tra thêm id có khớp không (nếu cần)
                if (userreq.getNameUser().equals(event.getNameUser())) {
                    userReadRepository.deleteById(userreq.getId());
                    System.out.println("Đã xóa thành công user có ID: " + userreq.getId());
                } else {
                    System.out.println("ID không khớp với user tìm được");
                }
            } else {
                System.out.println("Không tìm thấy user để xóa");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa user: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
