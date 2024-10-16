package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.exceptions.PermissionDenyException;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.Role;
import com.do_an.appointment.models.User;
import com.do_an.appointment.repositories.DoctorRepository;
import com.do_an.appointment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService{
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    @Override
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
    public String login(String phoneNumber, String password, Long roleId) throws Exception {
        return "";
    }

    @Override
    public void deleteUser(Long id) {

    }
}
