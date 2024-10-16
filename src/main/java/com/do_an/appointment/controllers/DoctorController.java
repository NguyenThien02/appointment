package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.dtos.UserDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.Role;
import com.do_an.appointment.models.User;
import com.do_an.appointment.repositories.RoleRepository;
import com.do_an.appointment.repositories.UserRepository;
import com.do_an.appointment.services.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<?> createDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO,
            BindingResult result
    ){
        try {
            if (result.hasErrors()) {
                List<String> errorMessager = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessager);
            }
            Doctor doctor = doctorService.crateDoctor(doctorDTO);
            return ResponseEntity.ok(doctor);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
