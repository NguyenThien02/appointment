package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.ProfileDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Profile;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/doctor")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO profileDTO){
        try {
            Profile profile = profileService.createProfile(profileDTO);
            return ResponseEntity.ok(profile);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/profile_id")
//    public ResponseEntity<?> getScheduleId(@PathVariable("profile_id") Long profileId){
//
//    }
}
