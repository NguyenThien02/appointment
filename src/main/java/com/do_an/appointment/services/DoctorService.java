package com.do_an.appointment.services;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.exceptions.PermissionDenyException;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.Role;
import com.do_an.appointment.models.Specialty;
import com.do_an.appointment.models.User;
import com.do_an.appointment.repositories.DoctorRepository;
import com.do_an.appointment.repositories.SpecialtyRepository;
import com.do_an.appointment.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService{
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;

    @Override
    @Transactional
    public Doctor crateDoctor(DoctorDTO doctorDTO) throws Exception {
        Long userId = doctorDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user id = " + userId));

        Long specialtyId = doctorDTO.getSpecialtyId();
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find specialty id = " + doctorDTO.getSpecialtyId()));

        if(!user.getRole().getName().equals(Role.DOCTOR)){
            throw new PermissionDenyException("You do not have the role of Doctor");
        }
        Doctor newDoctor = Doctor.builder()
                .experience(doctorDTO.getExperience())
                .build();
        newDoctor.setSpecialty(specialty);
        newDoctor.setUser(user);
        return doctorRepository.save(newDoctor);
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;  // Trả về true nếu xóa thành công
        } else {
            return false;  // Trả về false nếu không tìm thấy doctor
        }
    }

    @Override
    public Doctor uploadImageDoctor(Long id, String fileName) throws DataNotFoundException {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find doctor id = " + id));
        doctor.setImageUrl(fileName);
        return doctorRepository.save(doctor);
    }

    @Override
    public Page<Doctor> getAllDoctors(PageRequest pageRequest, Long speciallyId) {
        Page<Doctor> doctorPage;
        doctorPage = doctorRepository.searchDoctors(pageRequest, speciallyId);
        return doctorPage;
    }

    @Override
    public Doctor getDoctorByUserId(Long userId) {
        Doctor doctor = doctorRepository.findDoctorByUserId(userId);

        if (doctor == null || !doctor.getUser().getRole().getName().equals("DOCTOR")) {
            throw new IllegalArgumentException("User with provided ID is not a doctor or does not exist.");
        }

        return doctor;
    }

    @Override
    public Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO) throws DataNotFoundException {
        Doctor existingDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find doctor with id: " + doctorId));

        Specialty specialty = specialtyRepository.findById(doctorDTO.getSpecialtyId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find specialty with id: " + doctorDTO.getSpecialtyId()));

        existingDoctor.setSpecialty(specialty);
        existingDoctor.setExperience(doctorDTO.getExperience());
        return doctorRepository.save(existingDoctor);
    }


}
