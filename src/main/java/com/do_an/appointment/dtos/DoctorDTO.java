package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("specialty_id")
    private Long specialtyId;

    @JsonProperty("experience")
    private Long experience;

    @JsonProperty("image_url")
    private String imageUrl;
}
