package com.do_an.appointment.services;

import com.do_an.appointment.dtos.CheckTimeSlotDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    public Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception {
        User user = userRepository.findById(scheduleDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Không tìm thấy user với id: " + scheduleDTO.getUserId()));
        Doctor doctor = doctorRepository.findById(scheduleDTO.getDoctorId()).orElseThrow(() -> new DataNotFoundException("Không tìm thấy doctor với id: " + scheduleDTO.getDoctorId()));
        TimeSlot timeSlot = timeSlotRepository.findById(scheduleDTO.getTimeSlotId()).orElseThrow(() -> new DataNotFoundException("Không tìm thấy timeslot với id: " + scheduleDTO.getTimeSlotId()));

        Schedule newSchedule = Schedule.builder().user(user).userName(scheduleDTO.getUserName()).userPhone(scheduleDTO.getUserPhone()).doctor(doctor).date(scheduleDTO.getDate()).timeSlot(timeSlot).build();
        return scheduleRepository.save(newSchedule);
    }

    @Override
    public List<TimeSlot> checkTimeSlot(CheckTimeSlotDTO checkTimeSlotDTO) {
        // Lấy danh sách các timeSlot đã được đặt lịch cho bác sĩ và ngày chỉ định
        List<TimeSlot> bookedTimeSlots = scheduleRepository.findBookedTimeSlotsByDoctorAndDate(checkTimeSlotDTO.getDoctorId(), checkTimeSlotDTO.getDate());

        // Lấy tất cả các timeSlot
        List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();

        // Tìm các timeSlot còn trống bằng cách loại bỏ các timeSlot đã đặt khỏi danh sách tổng
        List<TimeSlot> availableTimeSlots = allTimeSlots.stream()
                .filter(timeSlot -> !bookedTimeSlots.contains(timeSlot))
                .toList();
        if (availableTimeSlots.isEmpty()) {
            return null;
        }
        return availableTimeSlots;
    }


    @Override
    public Page<Schedule> getScheduleByUserId(Long User_id, PageRequest pageRequest) {

        return scheduleRepository.findByUserId(User_id, pageRequest);
    }

    @Override
    public Schedule updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {

    }
}
