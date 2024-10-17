package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ServiceDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IServiceService {
    Service createService (ServiceDTO serviceDTO) throws Exception;

    Service getServiceById(Long id) throws Exception;

    List<Service> getAllService();

    Service updateService(Long id, ServiceDTO serviceDTO) throws DataNotFoundException;

    void deleteService(Long id);
}
