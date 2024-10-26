package com.do_an.appointment.responses;

import com.do_an.appointment.models.Schedule;
import com.do_an.appointment.models.Specialty;
import com.do_an.appointment.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_name")
    private String UserName;

    @JsonProperty("user_phone")
    private String UserPhone;

    @JsonProperty("doctor_name")
    private String doctorName;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("time_slot")
    private String timeSlot;



    public static ScheduleResponse fromSchedule(Schedule schedule){
        return ScheduleResponse.builder()
                .id(schedule.getId())
                .UserName(schedule.getUserName())
                .UserPhone(schedule.getUserPhone())
                .doctorName(schedule.getDoctor().getUser().getFullName())
                .date(schedule.getDate())
                .timeSlot(schedule.getTimeSlot().getStartEnd())
                .build();
    }
}
