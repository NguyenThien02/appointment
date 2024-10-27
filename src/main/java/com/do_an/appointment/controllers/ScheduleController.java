package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.CheckTimeSlotDTO;
import com.do_an.appointment.dtos.ScheduleDTO;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.TimeSlot;
import com.do_an.appointment.responses.DoctorResponse;
import com.do_an.appointment.responses.ScheduleListResponse;
import com.do_an.appointment.responses.ScheduleResponse;
import com.do_an.appointment.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<?> getScheduleUser(
            @PathVariable("user_id") Long userId,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit){
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("date").ascending()
        );
        Page<Schedule> scchedulePage = scheduleService.getScheduleByUserId(userId,pageRequest);
        Page<ScheduleResponse> scheduleResponsePage = scchedulePage.map(ScheduleResponse::fromSchedule);
        List<ScheduleResponse> scheduleResponses = scheduleResponsePage.getContent();
        int totalPages = scheduleResponsePage.getTotalPages();

        return ResponseEntity.ok(ScheduleListResponse.builder()
                .scheduleResponses(scheduleResponses)
                .totalPages(totalPages)
                .build());
    }
}
