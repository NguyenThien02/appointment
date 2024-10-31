package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.ProfileDetalDTO;
import com.do_an.appointment.models.Profile;
import com.do_an.appointment.models.Service;
import com.do_an.appointment.responses.ProfileResponse;
import com.do_an.appointment.services.ProfileDetailService;
import com.do_an.appointment.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/profileDetails")
@RequiredArgsConstructor
public class ProfileDetailController {

    private final ProfileDetailService profileDetailService;
    private final ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<?> createProfileDetails(@RequestBody ProfileDetalDTO profileDetalDTO) {
        try {
            profileDetailService.createProfileDetails(profileDetalDTO.getProfileId(), profileDetalDTO.getServiceIds());
            Profile profile = profileService.getProfileById(profileDetalDTO.getProfileId());

            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{profile_id}")
    private ResponseEntity<?> getServicesByProfileId(@PathVariable("profile_id") Long profileId){
        List<Service> serviceList = profileDetailService.getServicesByProfileId(profileId);
        return ResponseEntity.ok(serviceList);
    }
}
