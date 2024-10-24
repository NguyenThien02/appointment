package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.exceptions.PermissionDenyException;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.Role;
import com.do_an.appointment.models.User;
import com.do_an.appointment.repositories.DoctorRepository;
import com.do_an.appointment.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService{
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    @Override
    @Transactional
    public Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception {
        Long userId = doctorDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user id = " + userId));
        if(!user.getRole().getName().equals(Role.DOCTOR)){
            throw new PermissionDenyException("You do not have the role of Doctor");
        }
        Doctor newDoctor = Doctor.builder()
                .specialty(doctorDTO.getSpecialty())
                .experience(doctorDTO.getExperience())
                .build();
        newDoctor.setUser(user);
        return doctorRepository.save(newDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {

    }

    @Override
    public Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find doctor id = " + id));
        doctor.setImageUrl(fileName);
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }


}
