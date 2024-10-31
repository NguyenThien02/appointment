package com.do_an.appointment.responses;

import com.do_an.appointment.models.Profile;
import com.do_an.appointment.models.Schedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("schedule")
    private Schedule schedule;

    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("treatment")
    private String treatment;

    @JsonProperty("medications")
    private String medications;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("total_insurance_money")
    private Float totalInsuranceMoney;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public static ProfileResponse fromProfile(Profile profile){
        return ProfileResponse.builder()
                .id(profile.getId())
                .schedule(profile.getSchedule())

                .build();
    }

}
