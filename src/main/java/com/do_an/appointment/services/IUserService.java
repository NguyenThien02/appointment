package com.do_an.appointment.services;

import com.do_an.appointment.dtos.UserDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.User;

public interface IUserService {
    User crateUser(UserDTO userDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;// Trả về một Token key

    void deleteUser(Long id);

    User getUserByPhoneNumber(String phoneNumber) throws DataNotFoundException;
}
