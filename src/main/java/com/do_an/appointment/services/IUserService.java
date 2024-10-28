package com.do_an.appointment.services;

import com.do_an.appointment.dtos.PassWordDTO;
import com.do_an.appointment.dtos.UserDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    User crateUser(UserDTO userDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;// Trả về một Token key

    void deleteUser(Long id);

    User getUserByPhoneNumber(String phoneNumber) throws DataNotFoundException;

    User getUserDetailsFromToken(String token) throws Exception;

    User updateUserById(long id, UserDTO userDTO) throws DataNotFoundException;

    User updatePasswordById(long id, PassWordDTO passWordDTO) throws DataNotFoundException;

    Page<User> getAllUser(Pageable pageable);

    List<User> getUserDoctor();
}

