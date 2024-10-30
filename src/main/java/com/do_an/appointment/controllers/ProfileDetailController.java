package com.do_an.appointment.controllers;

import com.do_an.appointment.dtos.ProfileDetalDTO;
import com.do_an.appointment.services.ProfileDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/profileDetails")
@RequiredArgsConstructor
public class ProfileDetailController {

    private final ProfileDetailService profileDetailService;

    @PostMapping("")
    public ResponseEntity<?> createProfileDetails(@RequestBody ProfileDetalDTO profileDetalDTO) {
        try {
            profileDetailService.createProfileDetails(profileDetalDTO.getProfileId(), profileDetalDTO.getServiceIds());
            return ResponseEntity.ok("Profile details created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
