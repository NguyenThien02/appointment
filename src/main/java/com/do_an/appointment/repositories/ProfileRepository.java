package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByScheduleId(Long scheduleId);
}
