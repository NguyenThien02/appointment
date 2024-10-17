package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Doctor;
import org.springframework.web.multipart.MultipartFile;

public interface IDoctorService {
    Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception;

    String login(String phoneNumber, String password, Long roleId) throws Exception;// Trả về một Token key

    void deleteUser(Long id);

    Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException;
}
