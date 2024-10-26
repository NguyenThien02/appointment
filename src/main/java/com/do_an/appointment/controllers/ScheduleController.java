package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.repositories.ScheduleRepository;
import com.do_an.appointment.responses.ScheduleResponse;
import com.do_an.appointment.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("")
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws Exception {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO);
        ScheduleResponse scheduleResponse = ScheduleResponse.fromSchedule(schedule);
        return ResponseEntity.ok(scheduleResponse);
    }
}
