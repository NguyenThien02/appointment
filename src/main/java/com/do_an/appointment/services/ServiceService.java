package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ServiceDTO;
import com.do_an.appointment.models.Service;
import jakarta.transaction.Transactional;

public class ServiceService implements IServiceService{
    @Override
    @Transactional
    public Service createService(ServiceDTO serviceDTO) {

        return null;
    }

    @Override
    public Service getServiceById(Long id) {
        return null;
    }

    @Override
    public Service updateService(Long id, ServiceDTO serviceDTO) {
        return null;
    }

    @Override
    public void deleteService(Long id) {

    }
}
