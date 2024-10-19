package com.do_an.appointment.controllers;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import com.do_an.appointment.dtos.ServiceDTO;
import com.do_an.appointment.models.Service;
import com.do_an.appointment.services.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @PostMapping("")
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceDTO serviceDTO,
                                           BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessager = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessager);
            }
            Service service = serviceService.createService(serviceDTO);
            return ResponseEntity.ok(service);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getAllService( @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int limit,
                                            @RequestParam(defaultValue = "") String keyword,
                                            @RequestParam(defaultValue = "0", name = "category_id") Long categoryId
    ){
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("id").ascending()
        );
        Page<Service> servicePage = serviceService.getAllService(keyword, categoryId, pageRequest);
        return ResponseEntity.ok(servicePage);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getServiceById(@Valid @PathVariable("id") Long id) throws Exception {
        Service service = serviceService.getServiceById(id);
        return ResponseEntity.ok(service);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateService(@Valid @PathVariable("id") Long id,
                                           @RequestBody ServiceDTO serviceDTO
    ){
        try{
            Service service = serviceService.updateService(id,serviceDTO);
            return ResponseEntity.ok(service);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteServiceById(@Valid @PathVariable("id") Long id){
        serviceService.deleteService(id);
        return ResponseEntity.ok("Delete successfully");
    }
}
