package com.do_an.appointment.services;

import com.do_an.appointment.models.Category;
import com.do_an.appointment.models.Specialty;

import java.util.List;

public interface ISpecialtyService {
    List<Specialty> getAllSpecialties();
}
