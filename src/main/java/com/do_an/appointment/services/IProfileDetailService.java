package com.do_an.appointment.services;

import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Service;

import java.util.List;

public interface IProfileDetailService {
    String createProfileDetails(Long profileId, List<Long> serviceIds) throws DataNotFoundException;

    List<Service> getServicesByProfileId(Long profileId);
}
