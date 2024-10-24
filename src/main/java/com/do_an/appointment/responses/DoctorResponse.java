package com.do_an.appointment.responses;

import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("specialty")
    private String specialty;

    @JsonProperty("experience")
    private Long experience;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("user_response")
    private UserResponse userResponse;

    public static DoctorResponse fromDoctor(Doctor doctor){
        return DoctorResponse.builder()
                .id(doctor.getId())
                .specialty(doctor.getSpecialty())
                .experience(doctor.getExperience())
                .imageUrl(doctor.getImageUrl())
                .userResponse(UserResponse.fromUser(doctor.getUser()))
                .build();
    }
}
