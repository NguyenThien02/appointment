package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Doctor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDoctorService {
    Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception;

    void deleteDoctor(Long id);

    Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException;

    List<Doctor> getAllDoctors();
}
