package com.do_an.appointment.repositories;

import com.do_an.appointment.models.DoctorImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorImageRepository extends JpaRepository<DoctorImages, Long> {
}
