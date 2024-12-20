package com.do_an.appointment.services;

import com.do_an.appointment.dtos.CheckTimeSlotDTO;
import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IScheduleService {
    Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception;

    List<TimeSlot> checkTimeSlot(CheckTimeSlotDTO checkTimeSlotDTO);

    Page<Schedule> getScheduleByUserId(Long userId, PageRequest pageRequest);

    Page<Schedule> getScheduleByDoctorId(Long doctorId, PageRequest pageRequest);

    Schedule updateSchedule(Long id, ScheduleDTO scheduleDTO);

    void deleteSchedule(Long id);
}
