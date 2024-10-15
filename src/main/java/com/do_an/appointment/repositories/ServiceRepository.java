package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
