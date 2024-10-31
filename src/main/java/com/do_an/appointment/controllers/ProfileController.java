package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.ProfileDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Profile;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.responses.MessengerResponse;
import com.do_an.appointment.responses.ProfileResponse;
import com.do_an.appointment.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable("id") Long profileId){
        try {
            Profile profile = profileService.getProfileById(profileId);
            return ResponseEntity.ok(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Lấy ra danh sách profile theo doctor_id
    @GetMapping("/doctor/{doctor_id}")
    public ResponseEntity<List<Profile>> getProfilesByDoctorId(@PathVariable("doctor_id") Long doctorId) {
        List<Profile> profiles = profileService.getProfilesByDoctorId(doctorId);
        return ResponseEntity.ok(profiles);
    }

    @DeleteMapping("/{profile_id}")
    public ResponseEntity<?> deleteProfileById(@PathVariable("profile_id") Long profileId) throws DataNotFoundException {
        profileService.deleteProfileById(profileId);
        String messenger = "Xóa thành công profile với Id: " + profileId;
        MessengerResponse messengerResponse = new MessengerResponse();
        messengerResponse.setMessenger(messenger);
        return ResponseEntity.ok(messengerResponse);
    }
}
