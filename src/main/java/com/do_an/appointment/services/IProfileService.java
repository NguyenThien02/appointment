package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ProfileDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Profile;

import java.util.List;


public interface IProfileService {
    Profile createProfile(ProfileDTO profileDTO) throws Exception;

    Profile getProfileById(Long profileId) throws DataNotFoundException;

    List<Profile> getProfilesByDoctorId(Long doctorId);

    void deleteProfileById(Long profileId) throws DataNotFoundException;
}
