package com.do_an.appointment.responses;

import com.do_an.appointment.models.Service;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceListResponse {
    private List<Service> services;
    private int totalPages;
}
