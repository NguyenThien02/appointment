package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.User;

public interface IDoctorService {
    Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;// Trả về một Token key

    void deleteUser(Long id);
}
