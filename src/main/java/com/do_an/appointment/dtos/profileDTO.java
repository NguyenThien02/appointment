package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class profileDTO {
    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("treatment")
    private String treatment;

    @JsonProperty("medications")
    private String medications;

    @JsonProperty("total_money")
    private Float totalMoney;

}
