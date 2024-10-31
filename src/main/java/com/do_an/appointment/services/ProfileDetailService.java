package com.do_an.appointment.services;

import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Profile;
import com.do_an.appointment.models.ProfileDetail;
import com.do_an.appointment.models.Service;
import com.do_an.appointment.repositories.ProfileDetailRepository;
import com.do_an.appointment.repositories.ProfileRepository;
import com.do_an.appointment.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ProfileDetailService implements IProfileDetailService{
    private final ProfileDetailRepository profileDetailRepository;
    private final ProfileRepository profileRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public String createProfileDetails(Long profileId, List<Long> serviceIds) throws DataNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Profile not found"));
        for (Long serviceId : serviceIds) {
            Service service = serviceRepository.findById(serviceId)
                    .orElseThrow(() -> new DataNotFoundException("Service not found"));
            ProfileDetail profileDetail = ProfileDetail.builder()
                    .profile(profile)
                    .service(service)
                    .build();
            profileDetailRepository.save(profileDetail);
        }
        return "Profile details created successfully";
    }

    @Override
    public List<Service> getServicesByProfileId(Long profileId) {
        return profileDetailRepository.findServicesByProfileId(profileId);
    }

}
