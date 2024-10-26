package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeSlotDTO {
    @JsonProperty("start_end")
    private String startEnd;
}
