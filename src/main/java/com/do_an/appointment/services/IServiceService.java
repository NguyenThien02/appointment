package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ServiceDTO;
import com.do_an.appointment.models.Service;

public interface IServiceService {
    Service createService (ServiceDTO serviceDTO);

    Service getServiceById(Long id);

    Service updateService(Long id, ServiceDTO serviceDTO);

    void deleteService(Long id);
}
