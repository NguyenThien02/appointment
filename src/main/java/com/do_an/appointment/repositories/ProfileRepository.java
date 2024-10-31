package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    //Kiểm tra xem đã tồn tại scheduleId này chưa
    boolean existsByScheduleId(Long scheduleId);
    // Lấy ra danh sách profile theo doctor_id
    @Query("SELECT p FROM Profile p WHERE p.schedule.doctor.id = :doctorId")
    List<Profile> findProfilesByDoctorId(@Param("doctorId") Long doctorId);
}
