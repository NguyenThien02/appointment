package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Kiểm tra xem có bản ghi Schedule nào có cùng date, timeSlotId và doctorId không
    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.timeSlot.id = :timeSlotId AND s.doctor.id = :doctorId")
    Optional<Schedule> findByDateAndTimeSlotIdAndDoctorId(
            @Param("date") Date date,
            @Param("timeSlotId") Long timeSlotId,
            @Param("doctorId") Long doctorId
    );

    @Query("SELECT s.timeSlot FROM Schedule s WHERE s.doctor.id = :doctorId AND s.date = :date " )
    List<TimeSlot> findBookedTimeSlotsByDoctorAndDate(
            @Param("doctorId") Long doctorId,
            @Param("date") Date date);

    Page<Schedule> findByUserId(Long userId, PageRequest pageRequest);

    Page<Schedule> findByDoctorId(Long doctorId, PageRequest pageRequest);
}
