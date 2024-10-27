package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.CheckTimeSlotDTO;
import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.TimeSlot;
import com.do_an.appointment.responses.ScheduleResponse;
import com.do_an.appointment.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/check_timeSlot")
    public ResponseEntity<?> checkTimeSlot(@RequestBody CheckTimeSlotDTO checkTimeSlotDTO){
        List<TimeSlot> emptyTimeSlot =  scheduleService.checkTimeSlot(checkTimeSlotDTO);
        return ResponseEntity.ok(emptyTimeSlot) ;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getScheduleUser(@PathVariable("user_id") Long userId){
        List<Schedule> schedules = scheduleService.getScheduleByUserId(userId);
        List<ScheduleResponse> scheduleResponses = schedules.stream()
                .map(ScheduleResponse::fromSchedule)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scheduleResponses);
    }
}
