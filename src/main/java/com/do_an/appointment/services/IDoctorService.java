package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.util.List;

public interface IDoctorService {
    Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception;

    boolean deleteDoctorById(Long id);

    Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException;

    Page<Doctor> getAllDoctors(PageRequest pageRequest, Long specialyId);

    Doctor getDoctorByUserId(Long userId);

    Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO) throws DataNotFoundException;
}
