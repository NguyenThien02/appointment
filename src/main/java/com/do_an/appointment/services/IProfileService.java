package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ProfileDTO;
import com.do_an.appointment.models.Profile;

public interface IProfileService {
    Profile createProfile(ProfileDTO profileDTO) throws Exception;
}
