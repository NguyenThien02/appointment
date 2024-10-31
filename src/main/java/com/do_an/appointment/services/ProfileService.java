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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService implements  IProfileService{

    private final ScheduleRepository scheduleRepository;
    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(ProfileDTO profileDTO) throws Exception{
        if(profileRepository.existsByScheduleId(profileDTO.getScheduleId())){
            throw new DataNotFoundException("Lịch khám này đã được tạo profile");
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

    @Override
    public Profile getProfileById(Long profileId) throws DataNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy profile có id: " + profileId));
        return profile;
    }

    @Override
    public List<Profile> getProfilesByDoctorId(Long doctorId) {
        return profileRepository.findProfilesByDoctorId(doctorId);
    }

    @Override
    public void deleteProfileById(Long profileId) throws DataNotFoundException {
        profileRepository.findById(profileId)
                        .orElseThrow(() -> new DataNotFoundException("Không tìm thấy profile với Id: " + profileId));
        profileRepository.deleteById(profileId);
    }


}
