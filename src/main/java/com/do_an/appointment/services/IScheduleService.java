package com.do_an.appointment.services;

import com.do_an.appointment.dtos.CheckTimeSlotDTO;
import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.TimeSlot;

import java.util.List;

public interface IScheduleService {
    Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception;

    List<TimeSlot> checkTimeSlot(CheckTimeSlotDTO checkTimeSlotDTO);

    Schedule getScheduleByUserId(Long User_id);

    Schedule updateSchedule(Long id, ScheduleDTO scheduleDTO);

    void deleteSchedule(Long id);
}
