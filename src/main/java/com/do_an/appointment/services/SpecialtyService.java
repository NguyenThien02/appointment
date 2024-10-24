package com.do_an.appointment.services;

import com.do_an.appointment.models.Specialty;
import com.do_an.appointment.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService implements ISpecialtyService{
    private final SpecialtyRepository specialtyRepository;
    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }
}
