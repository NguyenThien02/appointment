package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Schedule;

public interface IScheduleService {
    Schedule createSchedule(ScheduleDTO scheduleDTO) throws Exception;

    Schedule getScheduleByUserId(Long User_id);

    Schedule updateSchedule(Long id, ScheduleDTO scheduleDTO);

    void deleteSchedule(Long id);
}
