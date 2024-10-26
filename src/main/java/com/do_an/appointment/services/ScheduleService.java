package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.TimeSlot;
import com.do_an.appointment.models.User;
import com.do_an.appointment.repositories.DoctorRepository;
import com.do_an.appointment.repositories.ScheduleRepository;
import com.do_an.appointment.repositories.TimeSlotRepository;
import com.do_an.appointment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    public Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception {
        if(scheduleRepository.existsByDateAndTimeSlotIdAndDoctorId(
                scheduleDTO.getDate(),
                scheduleDTO.getTimeSlotId(),
                scheduleDTO.getDoctorId()
        )){
            throw new DataIntegrityViolationException("Đã có người đặt");
        }
        User user = userRepository.findById(scheduleDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy user với id: " + scheduleDTO.getUserId()));
        Doctor doctor = doctorRepository.findById(scheduleDTO.getDoctorId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy doctor với id: " + scheduleDTO.getDoctorId()));
        TimeSlot timeSlot = timeSlotRepository.findById(scheduleDTO.getTimeSlotId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy timeslot với id: " + scheduleDTO.getTimeSlotId()));

        Schedule newSchedule = Schedule.builder()
                .user(user)
                .userName(scheduleDTO.getUserName())
                .userPhone(scheduleDTO.getUserPhone())
                .doctor(doctor)
                .date(scheduleDTO.getDate())
                .timeSlot(timeSlot)
                .build();
        return scheduleRepository.save(newSchedule);
    }

    @Override
    public Schedule getScheduleByUserId(Long User_id) {
        return null;
    }

    @Override
    public Schedule updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {

    }
}
