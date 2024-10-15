package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    @JsonProperty("doctor_id")
    private Long doctorId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("start_time")
    private LocalTime startTime;

    @JsonProperty("end_time")
    private LocalTime endTime;
}
