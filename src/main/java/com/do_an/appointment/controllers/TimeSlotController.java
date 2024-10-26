package com.do_an.appointment.controllers;

import com.do_an.appointment.models.TimeSlot;
import com.do_an.appointment.services.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/time_slots")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping("")
    public ResponseEntity<?> getAllTimeSlot(){
        List<TimeSlot> timeSlotList = timeSlotService.getAllTimeSlot();
        return ResponseEntity.ok(timeSlotList);
    }
}
