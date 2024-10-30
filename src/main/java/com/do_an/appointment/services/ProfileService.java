package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ProfileDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Profile;
import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.repositories.ProfileRepository;
import com.do_an.appointment.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService implements  IProfileService{

    private final ScheduleRepository scheduleRepository;
    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(ProfileDTO profileDTO) throws Exception{
        if(profileRepository.existsByScheduleId(profileDTO.getScheduleId())){
            throw new DataIntegrityViolationException("Schedule đã tồn tại");
        }

        Schedule schedule = scheduleRepository.findById(profileDTO.getScheduleId())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy schedule với id: " + profileDTO.getScheduleId()));
        Profile newProfile = Profile.builder()
                .schedule(schedule)
                .diagnosis(profileDTO.getDiagnosis())
                .treatment(profileDTO.getTreatment())
                .medications(profileDTO.getMedications())
                .build();
        return profileRepository.save(newProfile);
    }
}
