package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorImageDTO {
    @JsonProperty("doctor_id")
    private Long doctorId;

    @JsonProperty("image_url")
    @Size(min=5, max = 200,message = "Image's name")
    private String imageUrl;
}
