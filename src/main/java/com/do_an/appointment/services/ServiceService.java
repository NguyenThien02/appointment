package com.do_an.appointment.services;

import com.do_an.appointment.dtos.ServiceDTO;
import com.do_an.appointment.exceptions.DataNotFoundException;
import com.do_an.appointment.models.Category;
import com.do_an.appointment.models.Service;
import com.do_an.appointment.repositories.CategoryRepository;
import com.do_an.appointment.repositories.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService implements IServiceService{
    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;

    @Override
    @Transactional
    public Service createService(ServiceDTO serviceDTO) throws Exception {
        Category category = categoryRepository.findById(serviceDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find category with id: " + serviceDTO.getCategoryId() ));
        Service newService = Service.builder()
                .name(serviceDTO.getName())
                .description(serviceDTO.getDescription())
                .price(serviceDTO.getPrice())
                .insurancePrice(serviceDTO.getInsurancePrice())
                .build();
        newService.setCategory(category);
        return serviceRepository.save(newService);
    }

    @Override
    public Service getServiceById(Long id) throws Exception {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find service with Id: " + id));
    }

    @Override
    @Transactional
    public Service updateService(Long id, ServiceDTO serviceDTO) throws DataNotFoundException {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find Service with id: " + id));
        Category category = categoryRepository.findById(serviceDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find category with id: " + serviceDTO.getCategoryId()));
        existingService.setCategory(category);
        existingService.setName(serviceDTO.getName());
        existingService.setDescription(serviceDTO.getDescription());
        existingService.setPrice(serviceDTO.getPrice());
        existingService.setInsurancePrice(serviceDTO.getInsurancePrice());
        return serviceRepository.save(existingService);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
